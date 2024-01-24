import { fetchProductsByCategory } from "../js/master.js";
import { TABS, RICE_TYPE } from "./const.js";
import {
  ce,
  gebi,
  ac,
  addClasses,
  setHref,
  setSrc,
  setValue,
  removeClass,
} from "../js/utils.js";

const lists = gebi("lists");
const tabs = gebi("tabs");

window.addEventListener("DOMContentLoaded", async () => {
  let selectTab = 5;
  TABS.forEach(({ id, name }) => {
    const tab = ce("div");
    const tabClasses = ["tab"];
    if (id === selectTab) {
      tabClasses.push("active");
    }
    addClasses(tab, tabClasses);
    tab.innerText = name;
    setValue(tab, id);
    tab.addEventListener("click", async () => {
      selectTab = id;
      const tabItems = document.querySelectorAll(".tab");
      tabItems.forEach((tabItem) => {
        removeClass(tabItem, "active");
      });
      addClasses(tab, ["active"]);
      while (lists.firstChild) {
        lists.removeChild(lists.firstChild);
      }
      const data = await fetchProductsByCategory(selectTab);
      createProductList(data);
    });
    ac(tab, tabs);
  });
  const data = await fetchProductsByCategory(selectTab);
  createProductList(data);
});

const addCartDetail = async (option) => {
  await fetch("/hotmot/AddCartDetailServlet", {
    method: "POST",
    body: JSON.stringify(option),
  }).catch((err) => console.log("err: ", err));
};

const createProductList = (data) => {
  data.map((x) => {
    const listItem = ce("div");
    addClasses(listItem, ["list-item"]);
    setTimeout(() => {
      addClasses(listItem, ["show"]);
    }, 200);
    const linkEl = ce("a");
    setHref(linkEl, `/hotmot/ProductDetailServlet?id=${x.id}`);
    createImgEl({ src: x.image, parentEl: linkEl, className: "image" });
    ac(linkEl, listItem);
    const divEl = ce("div");
    addClasses(divEl, ["text-group"]);
    createH3El({ text: x.name, parentEl: divEl, className: "text" });
    createH3El({
      text: `${x.price}円 (税抜 : ${Math.ceil(x.price / 1.08)}円）`,
      parentEl: divEl,
      className: "text",
    });
    ac(divEl, listItem);
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
    addClasses(cartButton, ["cart-button"]);
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
      const option = [{ cartId: 1, productId: x.id, riceId, quantity }];
      await addCartDetail(option);
    });
    ac(cartButton, actionGroup);
    const bookMarkButton = ce("i");
    addClasses(bookMarkButton, [
      "fa-regular",
      "fa-star",
      "bookmark-button",
      "fa-2x",
    ]);
    bookMarkButton.style.color = "#FFCF81";
    bookMarkButton.addEventListener("click", async () => {
		console.log("book");
		await fetch("/hotmot/AddBookMarkServlet", {
    method: "POST",body: JSON.stringify({userId: "1", productId: "1", categoryId: "1"})
  }).catch((err) => console.log("err: ", err));
	})
    ac(bookMarkButton, actionGroup);
    ac(actionGroup, listItem);
    lists.appendChild(listItem);
  });
};

const createPEl = ({ text, className, parentEl }) => {
  const pEl = ce("p");
  pEl.innerText = text;
  if (className) {
    pEl.classList.add(className);
  }
  parentEl.appendChild(pEl);
};

const createH3El = ({ text, className, parentEl }) => {
  const pEl = ce("h3");
  pEl.innerText = text;
  if (className) {
    pEl.classList.add(className);
  }
  parentEl.appendChild(pEl);
};

const createImgEl = ({ src, alt, className, parentEl }) => {
  const imgEl = ce("img");
  setSrc(imgEl, src);
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
  const selectEl = ce("select");
  selectEl.addEventListener("change", (e) => {
    console.log(e.target.value);
    changeRiceIdFunc(Number(e.target.value));
  });
  addClasses(selectEl, [className]);
  options.forEach(({ id, name, price }) => {
    const optionEl = ce("option");
    optionEl.innerText = `${name} (${price}円)`;
    setValue(optionEl, id);
    ac(optionEl, selectEl);
  });
  if (className) {
    addClasses(selectEl, [className]);
  }
  ac(selectEl, parentEl);
};

const createEditQuantity = ({
  value,
  parentEl,
  addQuantityFunc,
  subQuantityFunc,
  changeQuantityFunc,
}) => {
  const divEl = ce("div");
  addClasses(divEl, ["counter"]);
  const inputEl = ce("input");
  setValue(inputEl, value);
  inputEl.type = "number";
  inputEl.addEventListener("input", (e) => {
    value = Number(e.target.value);
    setValue(inputEl, value);
    changeQuantityFunc(value);
    if (value > 0) {
      removeClass(subBtnEl, "disabled");
    } else if (value === 0) {
      addClasses(subBtnEl, ["disabled"]);
    }
  });
  const addBtnEl = ce("button");
  addBtnEl.innerText = "＋";
  addClasses(addBtnEl, ["add"]);
  addBtnEl.addEventListener("click", (e) => {
    value++;
    addQuantityFunc();
    console.log("value: ", value)
    setValue(inputEl, value);
    if (value > 0) {
      removeClass(subBtnEl, "disabled");
    }
    console.log("click", value);
  });
  const subBtnEl = ce("button");
  subBtnEl.classList.add("sub");
  subBtnEl.classList.add("disabled");
  subBtnEl.innerText = "－";
  subBtnEl.addEventListener("click", (e) => {
    value--;
    subQuantityFunc();
    setValue(inputEl, value);
    if (value === 0) {
      addClasses(subBtnEl, ["disabled"]);
    }
    console.log("click", value);
  });
  ac(subBtnEl, divEl);
  ac(inputEl, divEl);
  ac(addBtnEl, divEl);
  ac(divEl, parentEl);
};
