/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('calcForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        const address = document.getElementById('address').value;
        const netmask = document.getElementById('netmask').value;
        const subsupernet = document.getElementById('subsupernet').value;

        // Display "Hello" and the form data on the same screen
        const resultDiv = document.getElementById('result');
        resultDiv.innerHTML = `<h1>Hello</h1><p><strong>Address:</strong> ${address}</p><p><strong>Netmask:</strong> ${netmask}</p><p><strong>Netmask for Sub/Supernet:</strong> ${subsupernet}</p>`;
    });
});
