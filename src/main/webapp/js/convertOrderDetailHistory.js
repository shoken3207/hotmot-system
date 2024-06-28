import ProductData from "../jsons/Product.json" with { type: "json" };
import RiceData from "../jsons/Rice.json" with { type: "json" };

const createOrderDetailHistoriesResponse = (orderDetails) => {
  const productData = ProductData;
  const riceData = RiceData;
  const orderDetailHistories = orderDetails.map((orderDetail) => {
    const product = productData.find(({ id }) => id === orderDetail.productId);
    const rice = riceData.find(({ id }) => id === orderDetail.riceId);
    if (product && rice) {
      const {
        id: productId,
        name: productName,
        listImage: productImage,
        price,
      } = product;
      const { name: riceName } = rice;
      const { quantity, createdAt, id } = orderDetail;
      return {
        id,
        productName,
        productId,
        productImage,
        price: price + rice.price,
        riceName,
        quantity,
        createdAt,
      };
    }
  });
  const filterOrderDetailHistories = orderDetailHistories.filter(
    (x) => x !== undefined
  );

  return filterOrderDetailHistories;
};

export const createOrderHistoriesResponse = (orders) => {
  const orderHistories = orders.map((order) => {
    const { id, shopId, userId, details, createdAt } = order;
    const orderDetailHistories = createOrderDetailHistoriesResponse(details);

    return {
      id,
      shopId,
      userId,
      createdAt,
      details: orderDetailHistories,
    };
  });

  return orderHistories;
};

export const convertAdminHistories = (histories) => {
  const productData = ProductData;
  const riceData = RiceData;

  const convertHistories = histories.map((history) => {
    const product = productData.find(({ id }) => id === history.productId);
    const rice = riceData.find(({ id }) => id === history.riceId);
    if (product && rice) {
      const {
        id: productId,
        name: productName,
        listImage: productImage,
      } = product;
      const { name: riceName } = rice;
      const { quantity } = history;
      return {
        productName,
        productId,
        productImage,
        riceName,
        quantity,
      };
    }
  });

  return convertHistories;
};
