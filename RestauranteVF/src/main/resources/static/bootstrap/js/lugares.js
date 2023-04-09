// get all size buttons
const sizeButtons = document.querySelectorAll('.btn-size');

// loop through buttons and add click event listener
sizeButtons.forEach(button => {
  button.addEventListener('click', () => {
    // remove active class from all buttons
    sizeButtons.forEach(btn => {
      btn.classList.remove('active');
    });
    
    // add active class to clicked button
    button.classList.add('active');
    
    // set value of hidden input to selected size
    const sizeInput = document.querySelector('#product-size');
    sizeInput.value = button.dataset.size;
  });
});
