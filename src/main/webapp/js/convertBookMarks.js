import ProductData from "../jsons/Product.json" assert { type: "json" };

export const createBookMarksResponse = (bookMarks) => {
  const productData = ProductData;

  const bookMarksResponse = bookMarks.map((bookMark) => {
    const product = productData.find(({ id }) => id === bookMark.productId);
    if (product) {
      const {
        name: productName,
        listImage: productImage,
        id: productId,
        productCategoryId,
      } = product;
      const { id, userId, createdAt } = bookMark;
      return {
        id,
        userId,
        productId,
        productName,
        productImage,
        categoryId: productCategoryId,
        createdAt,
      };
    }
  });

  return bookMarksResponse;
};
