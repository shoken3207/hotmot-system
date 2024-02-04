import { createOrderHistoriesResponse } from '../js/convertOrderDetailHistory.js';
import {
  ce,
  gebi,
  ac,
  addClasses,
  removeClass,
  setSrc,
  setHref,
  showToast,
} from '../js/utils.js';

const orderHistoriesEl = gebi('orderHistory');
const orderHistoryListEl = gebi('orderHistoryList');
window.addEventListener('DOMContentLoaded', async () => {
  const orderHistories = JSON.parse(orderHistoriesEl.value);
  if (orderHistories.length === 0) {
    showToast({ text: '注文していません。' });
  }
  const convertOrderHistories = createOrderHistoriesResponse(orderHistories);
  console.log('orderHistories: ', orderHistories);
  console.log(convertOrderHistories);
  const detailsEl = ce('div');
  convertOrderHistories.forEach((orderHistory, index) => {
    const { createdAt, details } = orderHistory;
    const detailEl = ce('details');
    if (index === 0) {
      detailEl.open = true;
    }
    addClasses(detailEl, ['details']);
    const summaryEl = ce('summary');
    addClasses(summaryEl, ['details-summary']);
    const summaryContentEl = ce('div');
    addClasses(summaryContentEl, ['summary-content']);
    const dateEl = ce('span');
    dateEl.innerText = convertDate(createdAt);
    const totalAmountEl = ce('span');
    const totalAmount = calcTotal(details);
    totalAmountEl.innerText = `合計: ${totalAmount.toLocaleString()}円（税抜: ${Math.ceil(
      totalAmount / 1.08
    ).toLocaleString()}円）`;
    ac(dateEl, summaryContentEl);
    ac(totalAmountEl, summaryContentEl);
    const iconWrapEl = ce('span');
    const iconEl = ce('i');
    addClasses(iconWrapEl, ['icon']);
    addClasses(iconEl, ['fa-solid', 'fa-chevron-down']);
    ac(summaryContentEl, summaryEl);
    ac(iconEl, iconWrapEl);
    ac(iconWrapEl, summaryEl);
    ac(summaryEl, detailEl);
    const orderDetailListEl = ce('div');
    addClasses(orderDetailListEl, ['orderDetailList']);
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
  const detailBoxEl = ce('div');
  addClasses(detailBoxEl, ['orderDetailBox']);
  const linkEl = ce('a');
  setHref(linkEl, `/hotmot/ProductDetailServlet?id=${productId}`);
  const imageWrapEl = ce('div');
  addClasses(imageWrapEl, ['image']);
  const imageEl = ce('img');
  setSrc(imageEl, productImage);
  ac(imageEl, linkEl);
  ac(linkEl, imageWrapEl);
  // ac(imageEl, imageWrapEl);
  ac(imageWrapEl, detailBoxEl);
  const contentEl = ce('div');
  addClasses(contentEl, ['content']);
  const productNameEl = ce('h3');
  productNameEl.innerText = productName;
  const riceNameEl = ce('p');
  riceNameEl.innerText = riceName;
  const priceEl = ce('p');
  priceEl.innerText = `価格: ${price.toLocaleString()}円 (税抜 : ${Math.ceil(
    price / 1.08
  ).toLocaleString()}円）`;
  const quantityEl = ce('p');
  quantityEl.innerText = `個数: ${quantity}`;
  const subTotal = price * quantity;
  const subTotalEl = ce('p');
  subTotalEl.innerText = `小計: ${subTotal.toLocaleString()}円 (税抜 : ${Math.ceil(
    subTotal / 1.08
  ).toLocaleString()}円）`;
  ac(productNameEl, contentEl);
  ac(riceNameEl, contentEl);
  ac(quantityEl, contentEl);
  ac(priceEl, contentEl);
  ac(subTotalEl, contentEl);
  ac(contentEl, detailBoxEl);
  ac(detailBoxEl, parentEl);
};

const convertDate = (date) => {
  console.log(date);
  const newDate = new Date(date);
  return `${newDate.getFullYear()}年${
    newDate.getMonth() + 1
  }月${newDate.getDate()}日`;
};

const calcTotal = (details) => {
  const total = details.reduce((sum, { price, quantity }) => {
    return sum + price * quantity;
  }, 0);

  return total;
};
