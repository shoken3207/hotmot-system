/**
 * 
 */

import { gebi } from "../js/utils.js";
import {createBookMarksResponse} from "../js/convertBookMarks.js";

const messageEl = gebi("message");
const bookMarksEl = gebi("bookMarks");

const sampleData = [
  {
    id: 1,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 1,
  },
  {
    id: 2,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 1,
  },
  {
    id: 3,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 2,
  },
  {
    id: 4,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 2,
  },
  {
    id: 5,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 3,
  },
  {
    id: 6,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 3,
  },
  {
    id: 7,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 3,
  },
  {
    id: 8,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 4,
  },
  {
    id: 9,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 4,
  },
  {
    id: 10,
    userId: 1,
    productId: 1,
    productName: "梅おろし豚しゃぶ弁当",
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    categoryId: 4,
  },
];

 window.addEventListener("DOMContentLoaded", () => {
	 const message = messageEl.value;
	 const bookMarks = JSON.parse(bookMarksEl.value);
	 console.log("bookMarks: ", bookMarksEl, bookMarks);
	 const convertBookMarks = createBookMarksResponse(bookMarks);
	 console.log("convertBookMarks: ", convertBookMarks);
	 if(message) {
		 console.log(message)
		 Toastify({
  text: message,
  duration: 3000,
  destination: "https://github.com/apvarun/toastify-js",
  newWindow: true,
  close: true,
  gravity: "bottom", 
  position: "right", 
  stopOnFocus: true, 
  style: {
    background: "linear-gradient(to right, #00b09b, #96c93d)",
  },
  onClick: function(){} 
}).showToast();
	 }
	 
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

 })
 
 
