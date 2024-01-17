import { createCartDetailsResponse } from "../js/convertCartDetails.js";

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

//const fetchCartDetails = async () => {
//	const cartDetails = await fetch(`/hotmot/CartDetailListServlet?cartId=${cartId}`).then(res => console.log("res: ", res)).catch(err => console.log("err: ", err));
//	return cartDetails;
//}

//const cartIdEl = gebi("cartId");
//const cartId = cartIdEl.value;
const updateCartButtonEl = gebi("updateCart");
const orderButtonEl = gebi("order");
const cartDetailListEl = gebi("cartDetailList");



const sampleData = [
	{
		id: 1,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
	{
		id: 2,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
	{
		id: 3,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
	{
		id: 4,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
	{
		id: 5,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
	{
		id: 6,
		price: 590,
		productId: 1,
		productImage: "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
		productName: "梅おろし豚しゃぶ弁当",
		quantity: 3,
		riceName: "ライス: 小盛",
	},
]

window.addEventListener("DOMContentLoaded", async () => {
	console.log("call");
//	let cartDetails = await fetchCartDetails();
//	cartDetails = createCartDetailsResponse(cartDetails);
	const changeCartDetails = [];
	const change = ({id, quantity}) => {
		if(changeCartDetails.some((x) => id === x.id)) {
			const index = changeCartDetails.findIndex(x => x.id === id);
			changeCartDetails[index].quantity = quantity;
		} else {
			changeCartDetails.push({id, quantity})
		}
	}
	orderButtonEl.addEventListener("click", () => {
		console.log("order");
		
	})

	updateCartButtonEl.addEventListener("click", () => {
		console.log("update");
		console.log("change: ", changeCartDetails)
	})
	let cartDetails = sampleData;
	cartDetails.forEach(({id, price, productId, productImage, productName, quantity, riceName,}) => {
		const boxEl = ce("div");
		addClasses(boxEl, ["box"]);
		const imageWrapEl = ce("div");
		addClasses(imageWrapEl, ["image"])
		const imageEl = ce("img");
		imageEl.setAttribute("src", productImage);
		ac(imageEl, imageWrapEl);
		ac(imageWrapEl, boxEl);
		
		const rightEl = ce("div");
		addClasses(rightEl, ["right"]);
		const productNameEl = ce("h2");
		addClasses(productNameEl, ["productName"]);
		productNameEl.innerText = productName;
		const riceNameEl = ce("h3");
		addClasses(riceNameEl, ["riceName"]);
		riceNameEl.innerText = riceName;
		const priceEl = ce("h4");
		addClasses(priceEl, ["price"]);
		priceEl.innerText = `${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
		ac(productNameEl, rightEl);
		ac(riceNameEl, rightEl);
		ac(priceEl, rightEl);
		
		const addQuantityFunc = () => {
			quantity++;
			change({id, quantity});
		}
		const subQuantityFunc = () => {
			quantity--;
			change({id, quantity});
		}
		const changeQuantityFunc = (value) => {
			quantity = value;
			change({id, quantity});
		}
		createEditQuantity({value: quantity,parentEl: rightEl, addQuantityFunc, subQuantityFunc, changeQuantityFunc });
		ac(rightEl, boxEl);
		ac(boxEl, cartDetailListEl);
	})
	
})


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
  const addBtnEl = ce("button");
  addBtnEl.innerText = "＋";
  addClasses(addBtnEl, ["add"]);
  addBtnEl.addEventListener("click", (e) => {
    value++;
    addQuantityFunc();
    inputEl.value = value;
    if (value > 0) {
		removeClass(subBtnEl, "disabled");
    }
    console.log("click", value);
  });
  const subBtnEl = ce("button");
  addClasses(subBtnEl, ["sub"]);
  if(value === 0) {
	  addClasses(subBtnEl, ["disabled"]);
  }
  subBtnEl.innerText = "ー";
  subBtnEl.addEventListener("click", (e) => {
    value--;
    subQuantityFunc();
    inputEl.value = value;
    if (value === 0) {
		addClasses(subBtnEl, ["disabled"]);
    }
    console.log("click", value);
  });
  ac(subBtnEl, divEl);
  ac(inputEl, divEl);
  ac(addBtnEl, divEl);
  console.log("el: ", divEl, parentEl)
  ac(divEl, parentEl);
};