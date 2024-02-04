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
  if (cartDetails.length === 0) {
    showToast({ text: 'カートに商品がありません。' });
  } else {
    addClasses(actionGroupEl, ['disp']);
  }
  const convertCartDetails = createCartDetailsResponse(cartDetails);
  console.log('cartDetails: ', convertCartDetails);
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
    console.log('order');
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
          console.log(res.message);
          showToast({ text: res.message });
        }
        console.log(!res.isError);
        if (!res.isError) {
          window.location.href = `OrderHistoryServlet?userId=${userIdEl.value}`;
        }
      })
      .catch((err) => console.log('err', err));
  });

  updateCartButtonEl.addEventListener('click', async () => {
    console.log('change: ', changeCartDetails);
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
        changeCartDetails = [];
        if (res.message) {
          console.log(res.message);
          showToast({ text: res.message });
        }
      })
      .catch((err) => console.log('err', err));
  });
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
      priceEl.innerText = `${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
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
    console.log('click', value);
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
    console.log('click', value);
  });
  ac(subBtnEl, divEl);
  ac(inputEl, divEl);
  ac(addBtnEl, divEl);
  console.log('el: ', divEl, parentEl);
  ac(divEl, parentEl);
};
