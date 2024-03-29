import { createCartDetailsResponse } from '../js/convertCartDetails.js';
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
const updateCartButtonEl = gebi('updateCart');
const orderButtonEl = gebi('order');
const cartDetailListEl = gebi('cartDetailList');
const cartDetailsEl = gebi('cartDetails');
const userIdEl = gebi('userId');
const cartIdEl = gebi('cartId');
const actionGroupEl = gebi('actionGroup');

window.addEventListener('DOMContentLoaded', async () => {
  let cartDetails = JSON.parse(cartDetailsEl.value);
  const totalEl = ce('h4');
  addClasses(totalEl, ['total']);
  if (cartDetails.length === 0) {
    showToast({ text: 'カートに商品がありません。' });
  } else {
    addClasses(actionGroupEl, ['disp']);
    addClasses(totalEl, ['disp']);
  }
  const convertCartDetails = createCartDetailsResponse(cartDetails);
  let changeCartDetails = [];
  const change = ({ id, quantity }) => {
    if (changeCartDetails.some((x) => id === x.id)) {
      const index = changeCartDetails.findIndex((x) => x.id === id);
      changeCartDetails[index].quantity = quantity;
    } else {
      changeCartDetails.push({ id, quantity });
    }
  };
  orderButtonEl.addEventListener('click', async () => {
    await fetch('/hotmot/OrderServlet', {
      method: 'POST',
      body: JSON.stringify({
        cartId: Number(cartIdEl.value),
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((res) => {
        cartDetails = [];
        while (cartDetailListEl.firstChild) {
          cartDetailListEl.removeChild(cartDetailListEl.firstChild);
        }
        if (res.message) {
          showToast({ text: res.message });
        }
        if (!res.isError) {
          window.location.href = `OrderHistoryServlet?userId=${userIdEl.value}`;
        }
      })
      .catch((err) => console.log('err', err));
  });

  updateCartButtonEl.addEventListener('click', async () => {
    await fetch('/hotmot/UpdateCartDetailServlet', {
      method: 'POST',
      body: JSON.stringify(changeCartDetails),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((res) => {
		  changeCartDetails.forEach(detail => {
			  const {id, quantity} = detail;
			  const index = convertCartDetails.findIndex(x => x.id === id);
			  convertCartDetails[index].quantity = quantity;
		  })
		  const newTotal = calcTotal(convertCartDetails);
		  totalEl.innerText = `合計: ${newTotal.toLocaleString()}円 (税抜 : ${Math.ceil(
    newTotal / 1.08
  ).toLocaleString()}円）`;
        changeCartDetails = [];
        if (res.message) {
          showToast({ text: res.message });
        }
      })
      .catch((err) => console.log('err', err));
  });
  const total = calcTotal(convertCartDetails);

  totalEl.innerText = `合計: ${total.toLocaleString()}円 (税抜 : ${Math.ceil(
    total / 1.08
  ).toLocaleString()}円）`;
  ac(totalEl, cartDetailListEl);
  convertCartDetails.forEach(
    ({
      id,
      price,
      productId,
      productImage,
      productName,
      quantity,
      riceName,
    }) => {
      const boxEl = ce('div');
      addClasses(boxEl, ['box']);
      const linkEl = ce('a');
      setHref(linkEl, `/hotmot/ProductDetailServlet?id=${productId}`);
      const imageWrapEl = ce('div');
      addClasses(imageWrapEl, ['image']);
      const imageEl = ce('img');
      setSrc(imageEl, productImage);
      ac(imageEl, linkEl);
      ac(linkEl, imageWrapEl);
      ac(imageWrapEl, boxEl);

      const rightEl = ce('div');
      addClasses(rightEl, ['right']);
      const productNameEl = ce('h2');
      addClasses(productNameEl, ['productName']);
      productNameEl.innerText = productName;
      const riceNameEl = ce('h3');
      addClasses(riceNameEl, ['riceName']);
      riceNameEl.innerText = riceName;
      const priceEl = ce('h4');
      addClasses(priceEl, ['price']);
      priceEl.innerText = `${price.toLocaleString()}円 (税抜 : ${Math.ceil(
        price / 1.08
      ).toLocaleString()}円）`;
      ac(productNameEl, rightEl);
      ac(riceNameEl, rightEl);
      ac(priceEl, rightEl);

      const addQuantityFunc = () => {
        quantity++;
        change({ id, quantity });
      };
      const subQuantityFunc = () => {
        quantity--;
        change({ id, quantity });
      };
      const changeQuantityFunc = (value) => {
        quantity = value;
        change({ id, quantity });
      };
      createEditQuantity({
        value: quantity,
        parentEl: rightEl,
        addQuantityFunc,
        subQuantityFunc,
        changeQuantityFunc,
      });
      ac(rightEl, boxEl);
      ac(boxEl, cartDetailListEl);
    }
  );
});

const createEditQuantity = ({
  value,
  parentEl,
  addQuantityFunc,
  subQuantityFunc,
  changeQuantityFunc,
}) => {
  const divEl = ce('div');
  addClasses(divEl, ['counter']);
  const inputEl = ce('input');
  inputEl.value = value;
  inputEl.type = 'number';
  inputEl.addEventListener('input', (e) => {
    value = Number(e.target.value);
    inputEl.value = value;
    changeQuantityFunc(value);
    if (value > 0) {
      subBtnEl.classList.remove('disabled');
    } else if (value === 0) {
      subBtnEl.classList.add('disabled');
    }
  });
  const addBtnEl = ce('button');
  addBtnEl.innerText = '＋';
  addClasses(addBtnEl, ['add']);
  addBtnEl.addEventListener('click', (e) => {
    value++;
    addQuantityFunc();
    inputEl.value = value;
    if (value > 0) {
      removeClass(subBtnEl, 'disabled');
    }
  });
  const subBtnEl = ce('button');
  addClasses(subBtnEl, ['sub']);
  if (value === 0) {
    addClasses(subBtnEl, ['disabled']);
  }
  subBtnEl.innerText = '－';
  subBtnEl.addEventListener('click', (e) => {
    value--;
    subQuantityFunc();
    inputEl.value = value;
    if (value === 0) {
      addClasses(subBtnEl, ['disabled']);
    }
  });
  ac(subBtnEl, divEl);
  ac(inputEl, divEl);
  ac(addBtnEl, divEl);
  ac(divEl, parentEl);
};

const calcTotal = (details) => {
  const total = details.reduce((sum, { price, quantity }) => {
    return sum + price * quantity;
  }, 0);

  return total;
};
