import { fetchDetailProduct } from "../js/master.js";
import { RICE_TYPE } from "./const.js";
import { ce, gebi, ac, addClasses, setHref, setSrc } from "../js/utils.js";

window.addEventListener("DOMContentLoaded", async () => {
  const productIdEl = gebi("productId");
  const containerEl = gebi("container");
  const backBtn = gebi("backBtn");
  const referrer = document.referrer;

  let backLink = "";
  let backText = "";
  if (referrer.includes("ProductListServlet")) {
    backLink = "/hotmot/ProductListServlet";
    backText = "＜商品一覧画面に戻る";
  } else if (referrer.includes("CartDetailListServlet")) {
    backLink = "/hotmot/CartDetailListServlet";
    backText = "＜カート詳細一覧画面に戻る";
  } else if (referrer.includes("OrderHistoryServlet")) {
    backLink = "/hotmot/OrderHistoryServlet";
    backText = "＜注文履歴一覧画面に戻る";
  }
  setHref(backBtn, backLink);
  backBtn.innerText = backText;

  console.log("referrer: ", referrer);
  const productId = Number(productIdEl.value);
  console.log("productId: ", productId);
  const product = fetchDetailProduct(productId);
  const { name, price, image, desc } = product;
  const productNameEl = ce("h2");
  productNameEl.innerText = name;
  ac(productNameEl, containerEl);
  const priceEl = ce("h3");
  priceEl.innerText = `${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
  ac(priceEl, containerEl);
  const imageWrapEl = ce("div");
  const imageEl = ce("img");
  setSrc(imageEl, image);
  ac(imageEl, imageWrapEl);
  ac(imageWrapEl, containerEl);
  const descEl = ce("p");
  descEl.innerHTML = desc;
  ac(descEl, containerEl);

  const counterGroupEl = ce("div");
  addClasses(counterGroupEl, ["counter-group"]);
  createCounters(product.rices, counterGroupEl);
  ac(counterGroupEl, containerEl);
});

const createCounters = (rices, parentEl) => {
  rices.forEach((rice) => {
    console.log("rice: ", rice);
    const counterArea = ce("div");
    addClasses(counterArea, ["counter-area"]);
  });
};
