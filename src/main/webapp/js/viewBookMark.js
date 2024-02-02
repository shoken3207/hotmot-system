import {
  ce,
  gebi,
  ac,
  addClasses,
  removeClass,
  setSrc,
  setHref,
  setValue,
  showToast,
  rlc,
} from "../js/utils.js";
import { createBookMarksResponse } from "../js/convertBookMarks.js";
import { TABS, PRODUCT_CATEGORIES } from "../js/const.js";
const bookMarksEl = gebi("bookMarks");

const lists = gebi("lists");
const tabs = gebi("tabs");
const userIdEl = gebi("userId");
const cartIdEl = gebi("cartId");

window.addEventListener("DOMContentLoaded", () => {
  const bookMarks = JSON.parse(bookMarksEl.value);
  const convertBookMarks = createBookMarksResponse(bookMarks);
  const sessionSelectTab = Number(sessionStorage.getItem("bookMarkSelectTab"));
  let selectTab = sessionSelectTab || DEFAULT_SELECT_TAB;
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
      sessionStorage.setItem("bookMarkSelectTab", id);
      const tabItems = document.querySelectorAll(".tab");
      tabItems.forEach((tabItem) => {
        removeClass(tabItem, "active");
      });
      addClasses(tab, ["active"]);

      while (lists.firstChild) {
        lists.removeChild(lists.firstChild);
      }
      const filterBookMarks = filterByCategoryId(convertBookMarks, selectTab);
      createBookMarkList(filterBookMarks, lists);
    });
    ac(tab, tabs);
  });

  const filterBookMarks = filterByCategoryId(convertBookMarks, selectTab);
  createBookMarkList(filterBookMarks, lists);
});

const filterByCategoryId = (bookMarks, selectTab) => {
  let bookMarksByCategoryId = bookMarks;

  if (selectTab !== PRODUCT_CATEGORIES.ALL) {
    bookMarksByCategoryId = bookMarks.filter((x) => x.categoryId === selectTab);
  }
  if (bookMarksByCategoryId.length == 0) {
    showToast({ text: "お気に入りに登録している商品はありません。" });
  }
  return bookMarksByCategoryId;
};

const createBookMarkList = (bookMarks, parentEl) => {
  bookMarks.forEach((x, index) => {
    const listItem = ce("div");
    addClasses(listItem, ["list-item"]);
    setTimeout(() => {
      addClasses(listItem, ["show"]);
    }, 200);
    const linkEl = ce("a");
    setHref(linkEl, `/hotmot/ProductDetailServlet?id=${x.productId}`);
    const imageEl = ce("img");
    setSrc(imageEl, x.productImage);
    addClasses(imageEl, ["image"]);
    ac(imageEl, linkEl);
    ac(linkEl, listItem);
    const divEl = ce("div");
    addClasses(divEl, ["other-group"]);
    const productNameEl = ce("h3");
    addClasses(productNameEl, ["product-name"]);
    productNameEl.innerText = x.productName;
    ac(productNameEl, divEl);

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
          productId: x.productId,
          categoryId: x.categoryId,
        }),
      })
        .then((res) => {
          bookMarks.push({
            userId: Number(userIdEl.value),
            productId: x.productId,
            categoryId: x.categoryId,
          });
          rlc(divEl);
          ac(deleteBookMarkButton, divEl);
        })
        .catch((err) => console.log("err", err));
    });
    deleteBookMarkButton.addEventListener("click", async () => {
      await fetch("/hotmot/DeleteBookMarkServlet", {
        method: "POST",
        body: JSON.stringify({
          userId: Number(userIdEl.value),
          productId: x.productId,
        }),
      })
        .then((res) => {
          bookMarks.splice(index, 1);
          rlc(divEl);
          ac(addBookMarkButton, divEl);
        })
        .catch((err) => console.log("err", err));
    });
    if (bookMarks.some((bookMark) => bookMark.productId === x.productId)) {
      ac(deleteBookMarkButton, divEl);
    } else {
      ac(addBookMarkButton, divEl);
    }

    ac(divEl, listItem);
    parentEl.appendChild(listItem);
  });
};
