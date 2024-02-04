import { fetchDetailProduct } from '../js/master.js';
import { RICE_TYPE } from './const.js';
import {
  ce,
  gebi,
  ac,
  addClasses,
  setHref,
  setSrc,
  removeClass,
  rlc,
  showToast,
} from '../js/utils.js';

const bookMarksEl = gebi('bookMarks');
const bookMarks = JSON.parse(bookMarksEl.value);
const userIdEl = gebi('userId');
const cartIdEl = gebi('cartId');
window.addEventListener('DOMContentLoaded', async () => {
  const productIdEl = gebi('productId');
  const containerEl = gebi('container');
  const backBtn = gebi('backBtn');
  const referrer = document.referrer;

  let backLink = '';
  let backText = '';
  if (referrer.includes('ProductListServlet')) {
    backLink = `ProductListServlet?userId=${userIdEl.value}`;
    backText = '＜商品一覧画面に戻る';
  } else if (referrer.includes('BookMarkServlet')) {
    backLink = `BookMarkServlet?userId=${userIdEl.value}`;
    backText = '＜ブックマーク一覧画面に戻る';
  } else if (referrer.includes('CartDetailListServlet')) {
    backLink = `CartDetailListServlet?cartId=${cartIdEl.value}`;
    backText = '＜カート詳細一覧画面に戻る';
  } else if (referrer.includes('OrderHistoryServlet')) {
    backLink = `OrderHistoryServlet?userId=${userIdEl.value}`;
    backText = '＜注文履歴一覧画面に戻る';
  }
  setHref(backBtn, backLink);
  backBtn.innerText = backText;

  const productId = Number(productIdEl.value);
  const product = fetchDetailProduct(productId);
  const { name, price, image, desc, rices, allergys } = product;

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
      addCartDetails.push({
        cartId: Number(cartIdEl.value),
        productId,
        riceId,
        quantity,
      });
    }
  };

  const addCartDetailsFunc = async (option) => {
    await fetch('/hotmot/AddCartDetailServlet', {
      method: 'POST',
      body: JSON.stringify(option),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((res) => {
        console.log('success', option);
        if (res.message) {
          showToast({
            text: res.message,
            destination: `CartDetailListServlet?cartId=${cartIdEl.value}`,
          });
        }
        if (!res.isError) {
          window.location.href = `ProductListServlet?userId=${userIdEl.value}`;
        }
      })
      .catch((err) => console.log('err: ', err));
  };

  const createAllergys = ({ allergys, parentEl }) => {
    const allergyGroupEl = ce('div');
    addClasses(allergyGroupEl, ['allergy-group']);
    allergys.forEach(({ id, name }) => {
      const allergyEl = ce('div');
      addClasses(allergyEl, ['allergy']);
      const allergyTextEl = ce('span');
      allergyTextEl.innerText = name;
      ac(allergyTextEl, allergyEl);
      ac(allergyEl, allergyGroupEl);
    });
    ac(allergyGroupEl, parentEl);
  };

  const createEditQuantity = ({ rices, parentEl }) => {
    console.log('rices: ', rices, parentEl);
    rices.forEach(({ id, name, price }) => {
      let quantity = 0;
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
      const counterRowEl = ce('div');
      addClasses(counterRowEl, ['counter-group-child']);
      const textEl = ce('p');
      textEl.innerText = `${name}: ${price}`;
      const counterEl = ce('div');
      addClasses(counterEl, ['counter']);
      const inputEl = ce('input');
      inputEl.value = quantity;
      inputEl.type = 'number';
      inputEl.addEventListener('input', (e) => {
        changeQuantityFunc(Number(e.target.value));
        inputEl.value = quantity;
        if (quantity > 0) {
          subBtnEl.classList.remove('disabled');
        } else if (quantity === 0) {
          subBtnEl.classList.add('disabled');
        }
      });
      const addBtnEl = ce('button');
      addBtnEl.innerText = '＋';
      addClasses(addBtnEl, ['add']);
      addBtnEl.addEventListener('click', (e) => {
        addQuantityFunc();
        console.log('quantity: ', quantity);
        inputEl.value = quantity;
        if (quantity > 0) {
          removeClass(subBtnEl, 'disabled');
        }
        console.log('click', quantity);
      });
      const subBtnEl = ce('button');
      addClasses(subBtnEl, ['sub']);
      if (quantity === 0) {
        addClasses(subBtnEl, ['disabled']);
      }
      subBtnEl.innerText = '－';
      subBtnEl.addEventListener('click', (e) => {
        subQuantityFunc();
        inputEl.value = quantity;
        if (quantity === 0) {
          addClasses(subBtnEl, ['disabled']);
        }
        console.log('click', quantity);
      });
      ac(subBtnEl, counterEl);
      ac(inputEl, counterEl);
      ac(addBtnEl, counterEl);
      console.log('el: ', counterEl, parentEl);
      ac(textEl, counterRowEl);
      ac(counterEl, counterRowEl);
      ac(counterRowEl, parentEl);
    });
  };
  const topEl = ce('div');
  addClasses(topEl, ['top']);
  const productNameEl = ce('h2');
  productNameEl.innerText = name;
  ac(productNameEl, topEl);
  const bottomEl = ce('div');
  addClasses(bottomEl, ['bottom']);
  const leftEl = ce('div');
  addClasses(leftEl, ['left']);
  const imageWrapEl = ce('div');
  addClasses(imageWrapEl, ['image']);
  const imageEl = ce('img');
  setSrc(imageEl, image);
  ac(imageEl, imageWrapEl);
  ac(imageWrapEl, leftEl);
  const descEl = ce('p');
  descEl.innerHTML = desc;
  ac(descEl, leftEl);
  const rightEl = ce('div');
  addClasses(rightEl, ['right']);
  const priceEl = ce('h3');
  priceEl.innerText = `${price}円 (税抜 : ${Math.ceil(price / 1.08)}円）`;
  ac(priceEl, rightEl);
  ac(leftEl, bottomEl);
  const counterGroupEl = ce('div');
  addClasses(counterGroupEl, ['counter-group']);
  console.log('counterGroupEl', counterGroupEl);
  createEditQuantity({ rices, parentEl: counterGroupEl });
  ac(counterGroupEl, rightEl);
  const actionGroupEl = ce('div');
  addClasses(actionGroupEl, ['action-group']);
  const cartButton = ce('div');
  addClasses(cartButton, ['cart-button']);
  const cartButtonIcon = ce('i');
  addClasses(cartButtonIcon, ['fa-solid', 'fa-cart-shopping']);
  const cartButtonText = ce('span');
  cartButtonText.innerHTML = 'カートに<br />入れる';
  ac(cartButtonIcon, cartButton);
  ac(cartButtonText, cartButton);
  cartButton.addEventListener('click', async () => {
    if (addCartDetails.length === 0) return;
    console.log('addCartDetails: ', addCartDetails);
    await addCartDetailsFunc(addCartDetails);
  });
  ac(cartButton, actionGroupEl);
  const addBookMarkButton = ce('i');
  const deleteBookMarkButton = ce('i');
  addClasses(addBookMarkButton, [
    'fa-regular',
    'fa-bookmark',
    'bookmark-button',
    'fa-2x',
  ]);
  addClasses(deleteBookMarkButton, [
    'fa-solid',
    'fa-bookmark',
    'bookmark-button',
    'fa-2x',
  ]);
  addBookMarkButton.style.color = '#FFCF81';
  deleteBookMarkButton.style.color = '#FFCF81';
  addBookMarkButton.addEventListener('click', async () => {
    console.log('req: ', {
      userId: Number(userIdEl.value),
      productId: product.id,
      categoryId: product.categoryId,
    });
    await fetch('/hotmot/AddBookMarkServlet', {
      method: 'POST',
      body: JSON.stringify({
        userId: Number(userIdEl.value),
        productId: product.id,
        categoryId: product.categoryId,
      }),
    })
      .then((res) => {
        console.log('res: ', res);
        bookMarks.push({
          userId: Number(userIdEl.value),
          productId: product.id,
          categoryId: product.categoryId,
        });
        rlc(topEl);
        ac(deleteBookMarkButton, topEl);
      })
      .catch((err) => console.log('err', err));
  });
  deleteBookMarkButton.addEventListener('click', async () => {
    const deleteBookMark = bookMarks.find(
      (bookMark) => bookMark.productId === product.id
    );
    await fetch('/hotmot/DeleteBookMarkServlet', {
      method: 'POST',
      body: JSON.stringify({
        userId: Number(userIdEl.value),
        productId: deleteBookMark.productId,
      }),
    })
      .then((res) => {
        const deleteBookMarkIndex = bookMarks.findIndex(
          (bookMark) => bookMark.productId === product.id
        );
        bookMarks.splice(deleteBookMarkIndex, 1);
        rlc(topEl);
        ac(addBookMarkButton, topEl);
      })
      .catch((err) => console.log('err: ', err));
  });
  if (bookMarks.some((bookMark) => bookMark.productId === product.id)) {
    ac(deleteBookMarkButton, topEl);
  } else {
    ac(addBookMarkButton, topEl);
  }
  ac(actionGroupEl, rightEl);
  createAllergys({ allergys, parentEl: rightEl });
  ac(rightEl, bottomEl);
  ac(topEl, containerEl);
  ac(bottomEl, containerEl);
});
