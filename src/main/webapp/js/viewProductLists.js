import { fetchProductsByCategory } from "../js/master.js";
import { PRODUCT_CATEGORIES, TABS, RICE_TYPE } from "./const.js";

const lists = document.getElementById("lists");
const tabs = document.getElementById("tabs");

const ce = (el) => document.createElement(el);
const ctn = (el) => document.createTextNode(el);
const gebi = (id) => document.getElementById(id);
const qs = (query) => document.querySelector(query);
const ac = (child, parent) => parent.appendChild(child);
const addClasses = (el, classNames) =>{
	classNames.forEach(className => {
		el.classList.add(className);
	})
};
const removeClass = (el, className) => el.classList.remove(className);

window.addEventListener("DOMContentLoaded", async () => {
  let selectTab = 5;
  TABS.forEach(({ id, name }) => {
    const tab = document.createElement("div");
    tab.classList.add("tab");
    if (id === selectTab) {
      tab.classList.add("active");
    }
    tab.innerText = name;
    tab.setAttribute("value", id);
    tab.addEventListener("click", async () => {
      selectTab = id;
      const tabItems = document.querySelectorAll(".tab");
      tabItems.forEach((tabItem) => {
        tabItem.classList.remove("active");
      });
      tab.classList.add("active");
      while (lists.firstChild) {
        lists.removeChild(lists.firstChild);
      }
      const data = await fetchProductsByCategory(selectTab);
      createProductList(data);
    });
    tabs.appendChild(tab);
  });
  const data = await fetchProductsByCategory(selectTab);
  createProductList(data);
});

const addCartDetail = async (option) => {
  await fetch("/hotmot/AddCartDetailServlet", { method: "POST", body: JSON.stringify(option) }).catch(err => console.log("err: ", err));
};

const createProductList = (data) => {
  data.map((x) => {
    const listItem = document.createElement("div");
    listItem.classList.add("list-item");
    setTimeout(() => {
      listItem.classList.add("show");
    }, 200);
    createImgEl({ src: x.image, parentEl: listItem, className: "image" });
    const divEl = document.createElement("div");
    divEl.classList.add("text-group");
    createH3El({ text: x.name, parentEl: divEl, className: "text" });
    createH3El({ text: `${x.price}円 (税抜 : ${Math.ceil(x.price / 1.08)}円）`, parentEl: divEl, className: "text" });
    listItem.appendChild(divEl);
    let riceId = RICE_TYPE.NONE;
    const changeRiceIdFunc = (value) => (riceId = value);
    if (x.rices.length > 0) {
      riceId = x.rices[0].id;
      createSelecRicetEl({
        options: x.rices,
        parentEl: listItem,
        className: "select",
        changeRiceIdFunc,
      });
    }
    let quantity = 0;
    const addQuantityFunc = () => quantity++;
    const subQuantityFunc = () => quantity--;
    const changeQuantityFunc = (value) => (quantity = value);
    createEditQuantity({
      parentEl: divEl,
      value: quantity,
      addQuantityFunc,
      subQuantityFunc,
      changeQuantityFunc,
    });
    const actionGroup = ce("div");
    addClasses(actionGroup, ["action-group"]);
    const cartButton = ce("div");
    addClasses(cartButton, ["cart-button"])
    const cartButtonIcon = ce("i");
    addClasses(cartButtonIcon, ["fa-solid", "fa-cart-shopping"]);
    const cartButtonText = ce("span");
    cartButtonText.innerHTML = "カートに<br />入れる";
    ac(cartButtonIcon, cartButton);
    ac(cartButtonText, cartButton);
    cartButton.addEventListener("click", async () => {
      if (quantity === 0) return;
      console.log("click");
      console.log("id: ", x.id);
      console.log("riceId: ", riceId);
      console.log("quantity: ", quantity);
      const option = [{cartId: 1, productId: x.id, riceId, quantity }];
      await addCartDetail(option);
    });
    ac(cartButton, actionGroup);
    const bookMarkButton = ce("i");
    addClasses(bookMarkButton, ["fa-regular", "fa-star", "bookmark-button", "fa-2x"]);
    bookMarkButton.style.color = "#FFCF81"
    ac(bookMarkButton, actionGroup);
    ac(actionGroup, listItem);
    lists.appendChild(listItem);
  });
};

const createPEl = ({ text, className, parentEl }) => {
  const pEl = document.createElement("p");
  pEl.innerText = text;
  if (className) {
    pEl.classList.add(className);
  }
  parentEl.appendChild(pEl);
};

const createH3El = ({ text, className, parentEl }) => {
  const pEl = document.createElement("h3");
  pEl.innerText = text;
  if (className) {
    pEl.classList.add(className);
  }
  parentEl.appendChild(pEl);
};

const createImgEl = ({ src, alt, className, parentEl }) => {
  const imgEl = document.createElement("img");
  imgEl.setAttribute("src", src);
  if (alt) {
    imgEl.setAttribute("alt", alt);
  }
  if (className) {
    imgEl.classList.add(className);
  }
  parentEl.appendChild(imgEl);
};

const createSelecRicetEl = ({
  options,
  className,
  parentEl,
  changeRiceIdFunc,
}) => {
  const selectEl = document.createElement("select");
  selectEl.addEventListener("change", (e) => {
    console.log(e.target.value);
    changeRiceIdFunc(Number(e.target.value));
  });
  selectEl.classList.add(className);
  options.forEach(({ id, name, price }) => {
    const optionEl = document.createElement("option");
    optionEl.innerText = `${name} (${price}円)`;
    optionEl.setAttribute("value", id);
    selectEl.appendChild(optionEl);
  });
  if (className) {
    selectEl.classList.add(className);
  }
  parentEl.appendChild(selectEl);
};

const createEditQuantity = ({
  value,
  parentEl,
  addQuantityFunc,
  subQuantityFunc,
  changeQuantityFunc,
}) => {
  const divEl = document.createElement("div");
  divEl.classList.add("counter");
  const inputEl = document.createElement("input");
  inputEl.value = value;
  inputEl.type = "number";
  inputEl.addEventListener("input", (e) => {
    value = Number(e.target.value);
    inputEl.value = value;
    changeQuantityFunc(value);
    if (value > 0) {
      subBtnEl.classList.remove("disabled");
    }else if(value === 0) {
		subBtnEl.classList.add("disabled");
	}
  });
  const addBtnEl = document.createElement("button");
  addBtnEl.innerText = "＋";
  addBtnEl.classList.add("add");
  addBtnEl.addEventListener("click", (e) => {
    value++;
    addQuantityFunc();
    inputEl.value = value;
    if (value > 0) {
      subBtnEl.classList.remove("disabled");
    }
    console.log("click", value);
  });
  const subBtnEl = document.createElement("button");
  subBtnEl.classList.add("sub");
  subBtnEl.classList.add("disabled");
  subBtnEl.innerText = "ー";
  subBtnEl.addEventListener("click", (e) => {
    value--;
    subQuantityFunc();
    inputEl.value = value;
    if (value === 0) {
      subBtnEl.classList.add("disabled");
    }
    console.log("click", value);
  });
  divEl.appendChild(subBtnEl);
  divEl.appendChild(inputEl);
  divEl.appendChild(addBtnEl);
  parentEl.appendChild(divEl);
};
