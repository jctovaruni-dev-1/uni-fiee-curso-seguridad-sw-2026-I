const express = require('express');
const cookieParser = require('cookie-parser');
const app = express();
const port = 3000;

app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());

// Simulación de base de datos
let saldoHacker = 0;

const CSRF_TOKEN = getSecureCsrFToken();

function getSecureCsrFToken() {
    // En un entorno real, este token debería ser generado de forma segura y único por sesión
    return 'token_seguro_12345';
}

app.get('/', (req, res) => {
    // Simulamos que el usuario ya inició sesión y tiene esta cookie
    res.cookie('sesion_banco', 'usuario123_valido', { httpOnly: false }); 
    res.send(`
        <h1>Mi Banco Seguro</h1>
        <p>Saldo del Hacker: $${saldoHacker}</p>
        <form action="/transferir" method="POST">
            <input type="hidden" name="_csrf" value="${CSRF_TOKEN}">
            <input type="hidden" name="monto" value="100">
            <input type="hidden" name="cuenta" value="Hacker123">
            <button type="submit">Transferir $100 al Hacker (Prueba)</button>
        </form>
    `);
});

app.post('/transferir', (req, res) => {
    // El servidor CONFÍA ciegamente si la cookie existe
    if (req.cookies.sesion_banco === 'usuario123_valido' && 
        req.body._csrf === CSRF_TOKEN) {
        usuario = req.body.cuenta; // Extraemos el nombre de usuario
        console.log(`Transferencia realizada por: ${usuario}`);
        saldoHacker += parseInt(req.body.monto);
        console.log("¡Transferencia realizada con éxito!");
        res.send("<h1>Transferencia Exitosa</h1><a href='/'>Volver</a>");
    } else {
        res.status(401).send("No autenticado");
    }
});

//app.listen(3000, () => {
// console.log('Banco vulnerable en http://localhost:3000')
// });
app.listen(port, () => {
    console.log(`Banco vulnerable corriendo en http://localhost:${port}`);
});