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
  rlc,
  setId,
  showToast,
} from "../js/utils.js";

const lists = gebi("lists");
const tabs = gebi("tabs");

const bookMarksEl = gebi("bookMarks");
const bookMarks = JSON.parse(bookMarksEl.value);
const userIdEl = gebi("userId");
const cartIdEl = gebi("cartId");
window.addEventListener("DOMContentLoaded", async () => {
  //	const topScrollButtonEl = ce("a");
  //	setHref(topScrollButtonEl, "#")
  //	addClasses(topScrollButtonEl, ["top-scroll-button"]);
  //	const arrowIconEl = ce("i");
  //	arrowIconEl.style="color: white";
  //	addClasses(arrowIconEl, ["fa-solid", "fa-chevron-up", "fa-2x"])
  //	ac(arrowIconEl, topScrollButtonEl);
  //	ac(topScrollButtonEl, document.body);
  //	window.addEventListener("scroll", () => {
  //		console.log("scroll", window.scrollY)
  //		if(window.scrollY > 300) {
  //			if(!topScrollButtonEl.classList.contains('disp')) {
  //				addClasses(topScrollButtonEl, ["disp"])
  //			}
  //		}else {
  //			if(topScrollButtonEl.classList.contains('disp')) {
  //				removeClass(topScrollButtonEl, "disp")
  //			}
  //		}
  //	})
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

const addCartDetail = async (option, resetQuantityFunc) => {
  await fetch("/hotmot/AddCartDetailServlet", {
    method: "POST",
    body: JSON.stringify(option),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((res) => {
      console.log("success", option);
      const inputCountEl = gebi(`input-${option[0].productId}`);
      inputCountEl.value = 0;
      const subBtnEl = gebi(`sub-${option[0].productId}`);
      addClasses(subBtnEl, ["disabled"]);
      resetQuantityFunc();
      if (res.message) {
        console.log(res.message);
        showToast({ text: res.message });
      }
    })
    .catch((err) => console.log("err: ", err));
};

const createProductList = (data) => {
  while (lists.firstChild) {
    lists.removeChild(lists.firstChild);
  }
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
    const createEditQuantity = ({
      id,
      parentEl,
      addQuantityFunc,
      subQuantityFunc,
      changeQuantityFunc,
    }) => {
      const divEl = ce("div");
      addClasses(divEl, ["counter"]);
      const inputEl = ce("input");
      setId(inputEl, `input-${id}`);
      inputEl.value = quantity;
      inputEl.type = "number";
      inputEl.addEventListener("input", (e) => {
        changeQuantityFunc(Number(e.target.value));
        inputEl.value = quantity;
        if (quantity > 0) {
          removeClass(subBtnEl, "disabled");
        } else if (quantity === 0) {
          addClasses(subBtnEl, ["disabled"]);
        }
      });
      const addBtnEl = ce("button");
      addBtnEl.innerText = "＋";
      addClasses(addBtnEl, ["add"]);
      addBtnEl.addEventListener("click", (e) => {
        addQuantityFunc();
        inputEl.value = quantity;
        if (quantity > 0) {
          removeClass(subBtnEl, "disabled");
        }
      });
      const subBtnEl = ce("button");
      setId(subBtnEl, `sub-${id}`);
      subBtnEl.classList.add("sub");
      subBtnEl.classList.add("disabled");
      subBtnEl.innerText = "－";
      subBtnEl.addEventListener("click", (e) => {
        subQuantityFunc();
        inputEl.value = quantity;
        if (quantity === 0) {
          addClasses(subBtnEl, ["disabled"]);
        }
      });
      ac(subBtnEl, divEl);
      ac(inputEl, divEl);
      ac(addBtnEl, divEl);
      ac(divEl, parentEl);
    };
    const resetQuantityFunc = () => {
      quantity = 0;
    };
    const addQuantityFunc = () => quantity++;
    const subQuantityFunc = () => quantity--;
    const changeQuantityFunc = (value) => (quantity = value);
    createEditQuantity({
      id: x.id,
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
      const option = [
        { cartId: Number(cartIdEl.value), productId: x.id, riceId, quantity },
      ];
      await addCartDetail(option, resetQuantityFunc);
    });
    ac(cartButton, actionGroup);
    const addBookMarkButton = ce("i");
    const deleteBookMarkButton = ce("i");
    addClasses(addBookMarkButton, [
      "fa-regular",
      "fa-bookmark",
      "bookmark-button",
      "fa-2x",
    ]);
    addClasses(deleteBookMarkButton, [
      "fa-solid",
      "fa-bookmark",
      "bookmark-button",
      "fa-2x",
    ]);
    addBookMarkButton.style.color = "#FFCF81";
    deleteBookMarkButton.style.color = "#FFCF81";
    addBookMarkButton.addEventListener("click", async () => {
      await fetch("/hotmot/AddBookMarkServlet", {
        method: "POST",
        body: JSON.stringify({
          userId: Number(userIdEl.value),
          productId: x.id,
          categoryId: x.categoryId,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((res) => {
          console.log("res: ", res);
          bookMarks.push({
            userId: Number(userIdEl.value),
            productId: x.id,
            categoryId: x.categoryId,
          });
          rlc(actionGroup);
          ac(deleteBookMarkButton, actionGroup);
          if (res.message) {
            console.log(res.message);
            showToast({ text: res.message });
          }
        })
        .catch((err) => console.log("err", err));
    });
    deleteBookMarkButton.addEventListener("click", async () => {
      const deleteBookMark = bookMarks.find(
        (bookMark) => bookMark.productId === x.id
      );
      await fetch("/hotmot/DeleteBookMarkServlet", {
        method: "POST",
        body: JSON.stringify({
          userId: Number(userIdEl.value),
          productId: deleteBookMark.productId,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((res) => {
          const deleteBookMarkIndex = bookMarks.findIndex(
            (bookMark) => bookMark.productId === x.id
          );
          bookMarks.splice(deleteBookMarkIndex, 1);
          rlc(actionGroup);
          ac(addBookMarkButton, actionGroup);
          if (res.message) {
            console.log(res.message);
            showToast({ text: res.message });
          }
        })
        .catch((err) => console.log("err: ", err));
    });
    if (bookMarks.some((bookMark) => bookMark.productId === x.id)) {
      ac(deleteBookMarkButton, actionGroup);
    } else {
      ac(addBookMarkButton, actionGroup);
    }
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
