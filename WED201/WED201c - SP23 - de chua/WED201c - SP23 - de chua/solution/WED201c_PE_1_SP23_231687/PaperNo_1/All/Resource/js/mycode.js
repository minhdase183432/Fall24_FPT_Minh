function tourSelect() {
    let option = document.querySelector('#favourite-tour');
    //get ra cai price tour thong qua id
    let priceTour = document.querySelector('#price-tour');
    priceTour.innerText = option.value + '$/Person';
}

function CalTotal() {
    //get ra price
    let price = document.querySelector('#favourite-tour').value;
    //get total people
    let totalPeople = document.querySelector('#total-people').value;
    //cal total amount
    let totalAmount = price * totalPeople;
    //get ra div theo total amount
    let divTotalAmount = document.querySelector('#total-amount');
    //set inner text
    divTotalAmount.innerText = 'Total Amount: ' + totalAmount + '$';
}