
function updateProduct(productName, productPrice, productDescription, imageUrl, size) {

    //Trying to use Thymeleaf to get product details dynamically based on size
    //let productName = [[${product.name}]];
    //let productPrice = [[${product.price}]];
    //let productDescription = [[${product.description}]];

    //Determine the appropriate image file based on the selected size
    //let imageUrl = size === 'Large' ? '/static/images/large_candle.jpg' :
        //(size === 'Medium' ? '/static/images/medium_candle.jpg' :
           // (size === 'Small' ? '/static/images/small_candle.jpg' : '/static/images/large_candle.jpg'));


    document.getElementById('productName').innerText = productName;
    document.getElementById('productPrice').innerText = productPrice;
    document.getElementById('productDescription').innerText = productDescription;

    let productImage = document.getElementById('productImage');
    if (productImage) {
    productImage.src = imageUrl;
    }
}