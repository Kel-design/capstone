<!-- JavaScript to call the updateProduct function -->

    $(document).ready(function () {

    // Set initial quantity
    let quantity = 1;

    // Update the quantity input value
    function updateQuantity() {
    $('#quantity_input').val(quantity);
}

    // Increase quantity on plus button click
    $('#quantity_inc_button').click(function () {
    quantity++;
    updateQuantity();

});

    // Decrease quantity on minus button click
    $('#quantity_dec_button').click(function () {
    if (quantity > 1) {
    quantity--;
    updateQuantity();
}
});

    //handle add to cart button click (ADD IMAGE!)
    $('.shop-button').click(function () {
    let productName = $('#productName').text();
    let productPrice = $('#productPrice').text().replace('$', '');
    let productQuantity = $('#quantity_input').val();
    let productSize = $('.productSize').val();

    //NEED ACTION TO ADD THE PRODUCT TO THE shopping_cart in database

    //trying to log details to console just to see if it's working
    console.log('Added to cart:', productName, productPrice, productQuantity, productSize);
});

    // Set initial quantity
    updateQuantity();


});