import ProductData from "../jsons/Product.json" with { type: "json" };
import RiceData from "../jsons/Rice.json" with { type: "json" };

// 引数id, cartId, productId, riceId, quantity, createdAt
export const createCartDetailsResponse = (cartDetails) => {
  const productData = ProductData;
  const riceData = RiceData;
  const cartDetailsResponse = cartDetails.map((cartDetail) => {
    const product = productData.find(({ id }) => id === cartDetail.productId);
    const rice = riceData.find(({ id }) => id === cartDetail.riceId);
    if (product && rice) {
      const {
        id: productId,
        name: productName,
        listImage: productImage,
        price,
      } = product;
      const { name: riceName } = rice;
      const { quantity, createdAt, id } = cartDetail;
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
  const filterCartDetailsResponse = cartDetailsResponse.filter(
    (x) => x !== undefined
  );

  return filterCartDetailsResponse;
};