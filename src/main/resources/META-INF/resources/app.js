document.getElementById('create-book-form').addEventListener('submit', createBook);

function createBook(event) {
    event.preventDefault();

    let title = document.getElementById('title').value;
    let author = document.getElementById('author').value;

    fetch('http://localhost:8080/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            author: author
        })
    }).then(response => response.json())
        .then(book => {
            document.getElementById('title').value = '';
            document.getElementById('author').value = '';
            loadBooks();
        });
}

function loadBooks() {
    fetch('http://localhost:8080/books')
        .then(response => response.json())
        .then(books => {
            let booksTableBody = document.getElementById('books-table').querySelector('tbody');
            booksTableBody.innerHTML = '';
            books.forEach(book => {
                let row = document.createElement('tr');
                let titleCell = document.createElement('td');
                let authorCell = document.createElement('td');
                titleCell.textContent = book.title;
                authorCell.textContent = book.author;
                row.appendChild(titleCell);
                row.appendChild(authorCell);
                booksTableBody.appendChild(row);
            });
        });
}

loadBooks();
