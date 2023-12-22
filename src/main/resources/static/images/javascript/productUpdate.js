
function updateProduct(size){

    //Trying to use Thymeleaf to get product details dynamically based on size
    var productName = [[${product.name}]];
    var productPrice = [[${product.price}]];
    var productDescription = [[${product.description}]];

    //Determine the appropriate image file based on the selected size
    var imageUrl = size === 'Large' ? '../static/images/large_grey_candle.jpg' :
            (size === 'Medium' ? '../static/images/medium_clear_candle.jpg' :
            (size === 'Small' ? '../static/images/small_black_candle.jpg' : '../static/images/large_grey_candle.jpg' ));


    document.getElementById('productName').innerText = productName;
    document.getElementById('productPrice').innerText = productPrice;
    document.getElementById('productDescription').innerText = productDescription;
    document.getElementById('productImage').src = imageUrl;



}