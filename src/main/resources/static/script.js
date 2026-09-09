const btnBonjour = document.getElementById( "btnBonjour" );
const result = document.getElementById("result");


btnBonjour.addEventListener( "click", async() => 
{
    const response = await fetch( "/hello" );
    const text     = await response.text();

    result.textContent = text;
});


const userBtn = document.getElementById("userBtn");
const userResult = document.getElementById("userResult");

userBtn.addEventListener("click", async () => {

    const response = await fetch("/user");

    const data = await response.json();

    userResult.textContent =
        `${data.name} - ${data.age} ans`;
});



const scanBtn    = document.getElementById( "scanBtn"    );
const scanResult = document.getElementById( "scanResult" );

scanBtn.addEventListener( "click", async() =>
{
    const target   = document.getElementById("target").value;

    const response = await fetch("/scan",
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


const idBtn    = document.getElementById( "idBtn" );
const idResult = document.getElementById( "idResult" );

idBtn.addEventListener( "click", async() =>
{
    const data = await fetch("/testID/1290");
    const response = await data.text();

    idResult.textContent = response;
});