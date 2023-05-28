window.onload = function() {
    let params = new URLSearchParams(window.location.search);
    let bookId = params.get('id');
    console.log(bookId)
    loadBookDetails(bookId);

    document.getElementById('back-button').addEventListener('click', function() {
        window.location.href = '../index.html'; // Change this to the URL of your main page
    });
}

function loadBookDetails(id) {
    fetch(`http://localhost:8080/books/${id}`)
        .then(response => response.json())  // Change .json() to .text()
        .then(book => {
            console.log('Response body:', book);

            if(book) {
                document.getElementById('book-title').textContent = book.title;
                document.getElementById('book-author').textContent = book.author;
                document.getElementById('book-total').textContent = book.total;
                document.getElementById('book-description').textContent = book.description ? book.description : '-';
            } else {
                console.error('Received undefined book');
            }
        })
        .catch(error => {
            console.error('Failed to fetch book details:', error);
        });
}
