const registerForm       = document.getElementById('registerForm');
const registerFormResult = document.getElementById('registerFormResult');

registerForm.addEventListener( 'submit', async(event) =>
{
    event.preventDefault();

    const formData  = new FormData(event.target);
    const objetData = Object.fromEntries(formData.entries());
    const jsonForm  = JSON.stringify(objetData);

    console.log(`Nom : ${objetData.nom}`);

    const dataFetch = await fetch('/register', 
    {
        method: "POST",

        headers: { "Content-Type": "application/json" },
        
        body: jsonForm
    });

    const response = await dataFetch.text();
    registerFormResult.textContent = response;
});