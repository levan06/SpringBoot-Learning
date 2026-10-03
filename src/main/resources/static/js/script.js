/*=====================*/
/*   Bouton Bonjour    */
/*=====================*/

const btnBonjour = document.getElementById( "btnBonjour" );
const result = document.getElementById("result");


btnBonjour.addEventListener( "click", async() => 
{
    const response = await fetch( "/api/hello" );
    const text     = await response.text();

    result.textContent = text;
});


/*=================================*/
/*   Bouton Charger Utilisateur    */
/*=================================*/
const userBtn = document.getElementById("userBtn");
const userResult = document.getElementById("userResult");

userBtn.addEventListener("click", async () => {

    const response = await fetch("/user");

    const data = await response.json();

    userResult.textContent =
        `${data.name} - ${data.age} ans`;
});


/*=====================*/
/*   Bouton Scanner    */
/*=====================*/
const scanBtn    = document.getElementById( "scanBtn"    );
const scanResult = document.getElementById( "scanResult" );

scanBtn.addEventListener( "click", async() =>
{
    const target   = document.getElementById("target").value;

    const response = await fetch("/api/scan",
    {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            target: target
        })
    });

    const data = await response.json();

    scanResult.textContent = data.message + "\n" + data.nmap;
});


/*=====================*/
/*   Bouton Id user    */
/*=====================*/
const idBtn    = document.getElementById( "idBtn" );
const idResult = document.getElementById( "idResult" );

idBtn.addEventListener( "click", async() =>
{
    const data = await fetch("/api/testID/1290");
    const response = await data.text();

    idResult.textContent = response;
});


/*=========================*/
/*   Bouton Create user    */
/*=========================*/
const createUserFrom   = document.getElementById( 'createUserFrom' );
const createUserResult = document.getElementById( 'createUserResult' ); 

createUserFrom.addEventListener( 'submit', async(event) => 
{    
    event.preventDefault();

    // Récupèrer automatiquement les champs grâce à l'attribut 'name'
    const formData = new FormData(event.target);
    
    // Transformer le FormData en un objet JavaScript simple
    const objetData = Object.fromEntries(formData.entries());
    
    // Convertir l'objet en texte au format JSON
    const jsonEnvoye = JSON.stringify(objetData);

    // Test
    console.log("JSON généré qui sera envoyé :", jsonEnvoye);

    // Envoyer au backend
    const data = await fetch('/api/users', 
    {
        method: "POST",

        headers: { "Content-Type": "application/json" },
        
        body: jsonEnvoye
    });

    const response = await data.text();
    createUserResult.textContent = response;
});