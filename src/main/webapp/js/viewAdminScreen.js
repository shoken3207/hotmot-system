import {
  convertAdminHistories,
  createOrderHistoriesResponse,
} from "../js/convertOrderDetailHistory.js";
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
const userIdEl = gebi("userId");
const historiesEl = gebi("histories");

const getDate = (arg_date) => {
  let now;
  if (arg_date) {
    now = new Date(arg_date);
  } else {
    now = new Date();
  }
  const year = now.getFullYear().toString().padStart(2, "0");
  const month = (now.getMonth() + 1).toString().padStart(2, "0");
  const date = now.getDate().toString().padStart(2, "0");
  return { year, month, date };
};

const fetchOrderDetailHistories = async (fromDate, toDate) => {
  const histories = await fetch(
    `/hotmot/AdminScreenServlet?userId=${userIdEl.value}&fromDate=${fromDate}&toDate=${toDate}`,
    {
      method: "GET",
    }
  )
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((res) => {
      if (res.message) {
        showToast({ text: res.message });
      }
      if (res.length === 0) {
        showToast({ text: "この日は注文がありません。" });
      }
      const histories = convertAdminHistories(res);
      return histories;
    })
    .catch((err) => console.log("err", err));
  return histories;
};

const viewAdminScreen = (histories, parentEl) => {
  histories.forEach((history) => {
    const { productName, productImage, riceName, quantity } = history;
    const historyBoxEl = ce("div");
    addClasses(historyBoxEl, ["history-box"]);
    const imageEl = ce("div");
    addClasses(imageEl, ["image"]);
    const imgEl = ce("img");
    setSrc(imgEl, productImage);
    ac(imgEl, imageEl);
    const contentEl = ce("div");
    addClasses(contentEl, ["content"]);
    const productNameEl = ce("h3");
    addClasses(productNameEl, ["productName"]);
    productNameEl.innerText = productName;
    const riceNameEl = ce("p");
    addClasses(riceNameEl, ["riceName"]);
    riceNameEl.innerText = riceName;
    const quantityEl = ce("p");
    addClasses(quantityEl, ["quantity"]);
    quantityEl.innerText = `個数: ${quantity}`;
    ac(productNameEl, contentEl);
    ac(riceNameEl, contentEl);
    ac(quantityEl, contentEl);
    ac(imageEl, historyBoxEl);
    ac(contentEl, historyBoxEl);
    ac(historyBoxEl, parentEl);
  });
};

const selectDateEl = gebi("selectDate");
window.addEventListener("DOMContentLoaded", async () => {
  const { year, month, date } = getDate();
  selectDateEl.value = `${year}-${month}-${date}`;
  selectDateEl.addEventListener("change", async (e) => {
    while (historiesEl.firstChild) {
      historiesEl.removeChild(historiesEl.firstChild);
    }
    const { year, month, date } = getDate(e.target.value);
    const fromDate = `${year}-${month}-${date} 00:00:00`;
    const toDate = `${year}-${month}-${date} 23:59:59`;
    const histories = await fetchOrderDetailHistories(fromDate, toDate);
    viewAdminScreen(histories, historiesEl);
  });
  const fromDate = `${year}-${month}-${date} 00:00:00`;
  const toDate = `${year}-${month}-${date} 23:59:59`;

  const histories = await fetchOrderDetailHistories(fromDate, toDate);
  viewAdminScreen(histories, historiesEl);
});
