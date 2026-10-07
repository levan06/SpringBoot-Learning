const loginForm = document.getElementById('loginForm');
const loginFormResult = document.getElementById('loginFormResult');

loginForm.addEventListener( 'submit', async(event) =>
{
    event.preventDefault();

    const formData  = new FormData(event.target);
    const objetData = Object.fromEntries(formData.entries());
    const jsonForm  = JSON.stringify(objetData);

    try 
    {
        const dataFetch = await fetch('/login',
        {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: jsonForm
        });

        const response = await dataFetch.text();
        loginFormResult.textContent = response;

        if( dataFetch.ok && response.trim() === "Valid Account" )
        {
            console.log("Redirecting...");
            // The login page is removed from history. "Back" button won't break things.
            window.location.replace("dashboard.html");
        }
    } catch (error) {
        loginFormResult.textContent = "Une erreur est survenue. Réessayez.";
        console.error("Registration failed:", error);
    }
});