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

  const productId = Number(productIdEl.value);
  const product = fetchDetailProduct(productId);
  const { name, price, image, desc, rices } = product;

  const addCartDetails = [];
  const add = ({ riceId, quantity }) => {
    if (
      addCartDetails.some(
        (x) => productId === x.productId && riceId === x.riceId
      )
    ) {
      const index = addCartDetails.findIndex(
        (x) => x.productId === productId && x.riceId === riceId
      );
      if (quantity === 0) {
        addCartDetails.splice(index, 1);
      } else {
        addCartDetails[index].quantity = quantity;
      }
    } else {
      addCartDetails.push({ productId, riceId, quantity });
    }
  };
  
  const createEditQuantity = ({ rices, parentEl }) => {
		console.log("rices: ", rices, parentEl)
	  rices.forEach(({ id, name, price }) => {
	    const quantity = 0;
	    const addQuantityFunc = () => {
	      quantity++;
	      add({ riceId: id, quantity });
	    };
	    const subQuantityFunc = () => {
	      quantity--;
	      add({ riceId: id, quantity });
	    };
	    const changeQuantityFunc = (value) => {
	      quantity = value;
	      add({ riceId: id, quantity });
	    };
	    const counterRowEl = ce("div");
	    const textEl = ce("p");
	    textEl.innerText = `${name}: ${price}`;
	    const counterEl = ce("div");
	    addClasses(counterEl, ["counter"]);
	    const inputEl = ce("input");
	    inputEl.value = quantity;
	    inputEl.type = "number";
	    inputEl.addEventListener("input", (e) => {
	      changeQuantityFunc(Number(e.target.value));
	      inputEl.value = quantity;
	      if (quantity > 0) {
	        subBtnEl.classList.remove("disabled");
	      } else if (quantity === 0) {
	        subBtnEl.classList.add("disabled");
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
	      console.log("click", quantity);
	    });
	    const subBtnEl = ce("button");
	    addClasses(subBtnEl, ["sub"]);
	    if (quantity === 0) {
	      addClasses(subBtnEl, ["disabled"]);
	    }
	    subBtnEl.innerText = "ー";
	    subBtnEl.addEventListener("click", (e) => {
	      subQuantityFunc();
	      inputEl.value = quantity;
	      if (quantity === 0) {
	        addClasses(subBtnEl, ["disabled"]);
	      }
	      console.log("click", quantity);
	    });
	    ac(subBtnEl, counterEl);
	    ac(inputEl, counterEl);
	    ac(addBtnEl, counterEl);
	    console.log("el: ", counterEl, parentEl);
	    ac(textEl, counterRowEl);
	    ac(counterEl, counterRowEl);
	    ac(counterRowEl, parentEl);
	  });
	};

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
  console.log("counterGroupEl", counterGroupEl);
  createEditQuantity({rices, parentEl: counterGroupEl});
  ac(counterGroupEl, containerEl);
});

