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
  console.log("orderHistories: ",orderHistories )
  const convertOrderHistories = createOrderHistoriesResponse(orderHistories);
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
  const productNameEl = ce("h3");
  productNameEl.innerText = productName;
  const productImageEl = ce("img");
  setSrc(productImageEl, productImage);
  const riceNameEl = ce("p");
  riceNameEl.innerText = riceName;
  const quantityEl = ce("p");
  quantityEl.innerText = `個数: ${quantity}`;
  const priceEl = ce("p");
  priceEl.innerText = `${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
  const subTotal = price * quantity;
  const subTotalEl = ce("p");
  subTotalEl.innerText = `小計: ${subTotal}円 (税抜 : ${Math.ceil(
    subTotal / 1.08
  )}円）`;
  ac(productNameEl, detailBoxEl);
  ac(productImageEl, detailBoxEl);
  ac(riceNameEl, detailBoxEl);
  ac(quantityEl, detailBoxEl);
  ac(priceEl, detailBoxEl);
  ac(subTotalEl, detailBoxEl);
  ac(detailBoxEl, parentEl);
};


const convertDate = (date) => {
	const newDate = new Date(date);
	return `${newDate.getFullYear()}/${newDate.getMonth() + 1}/${newDate.getDate()}`;
}
