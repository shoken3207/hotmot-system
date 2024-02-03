import { createOrderHistoriesResponse } from "../js/convertOrderDetailHistory.js";
import {
  ce,
  gebi,
  ac,
  addClasses,
  removeClass,
  setSrc,
  setHref,
  showToast,
} from "../js/utils.js";

const orderHistoriesEl = gebi("orderHistory");
const orderHistoryListEl = gebi("orderHistoryList");
window.addEventListener("DOMContentLoaded", async () => {
  const orderHistories = JSON.parse(orderHistoriesEl.value);
  if (orderHistories.length === 0) {
    showToast({ text: "注文していません。" });
  }
  console.log("orderHistories: ", orderHistories);
  const convertOrderHistories = createOrderHistoriesResponse(orderHistories);
  console.log(convertOrderHistories);
  const detailsEl = ce("div");
  convertOrderHistories.forEach((orderHistory) => {
    const { createdAt, details } = orderHistory;
    const detailEl = ce("details");
    addClasses(detailEl, ["details"]);
    const summaryEl = ce("summary");
    addClasses(summaryEl, ["details-summary"]);
    const dateEl = ce("span");
    dateEl.innerText = convertDate(createdAt);
    const totalAmountEl = ce("span");
    totalAmountEl.innerText = "合計金額: 1,080円（税抜: 1,000円）";
    ac(dateEl, summaryEl);
    ac(totalAmountEl, summaryEl);
    ac(summaryEl, detailEl);
    const orderDetailListEl = ce("div");
    addClasses(orderDetailListEl, ["orderDetailList"]);
    details.forEach((detail) => {
      createOrderDetailHistory(detail, orderDetailListEl);
    });
    ac(orderDetailListEl, detailEl);
    ac(detailEl, detailsEl);
  });
  ac(detailsEl, orderHistoryListEl);
});

const createOrderDetailHistory = (orderDetailHistory, parentEl) => {
  const { productName, productImage, quantity, riceName, price, productId } =
    orderDetailHistory;
  const detailBoxEl = ce("div");
  addClasses(detailBoxEl, ["orderDetailBox"]);
  const imageWrapEl = ce("div");
  addClasses(imageWrapEl, ["image"]);
  const imageEl = ce("img");
  setSrc(imageEl, productImage);
  ac(imageEl, imageWrapEl);
  ac(imageWrapEl, detailBoxEl);
  const contentEl = ce("div");
  addClasses(contentEl, ["content"]);
  const productNameEl = ce("h3");
  productNameEl.innerText = productName;
  const riceNameEl = ce("p");
  riceNameEl.innerText = `ライス: ${riceName}`;
  const priceEl = ce("p");
  priceEl.innerText = `価格: ${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
  const quantityEl = ce("p");
  quantityEl.innerText = `個数: ${quantity}`;
  const subTotal = price * quantity;
  const subTotalEl = ce("p");
  subTotalEl.innerText = `小計: ${subTotal}円 (税抜 : ${Math.ceil(
    subTotal / 1.08
  )}円）`;
  ac(productNameEl, contentEl);
  ac(riceNameEl, contentEl);
  ac(priceEl, contentEl);
  ac(quantityEl, contentEl);
  ac(subTotalEl, contentEl);
  ac(contentEl, detailBoxEl);
  ac(detailBoxEl, parentEl);
};

const convertDate = (date) => {
  const newDate = new Date(date);
  return `${newDate.getFullYear()}/${
    newDate.getMonth() + 1
  }/${newDate.getDate()}`;
};
