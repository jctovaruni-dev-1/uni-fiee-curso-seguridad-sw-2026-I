const express = require('express');
const app = express();
const port = 3000;

function sanitizeInput(input) {
    return input.replace(/[&<>"']/g, function(match) {
        switch (match) {
            case '&': return '&amp;';
            case '<': return '&lt;';
            case '>': return '&gt;';
            case '"': return '&quot;';
            case "'": return '&#39;';
        }[match];
    });
}

app.use((req, res, next) => {
    res.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self';");
    next();
});

app.get('/', (req, res) => {
    // El servidor recibe el parámetro 'nombre' y lo refleja sin limpiar
    const nombre = sanitizeInput(req.query.nombre) || "Invitado";
    //const nombre = req.query.nombre || "Invitado";
    
    res.send(`
        <html>
            <body>
                <h1>Bienvenido a la Intranet</h1>
                <p>Hola, ${nombre}!</p>
                <hr>
                <form action="/" method="GET">
                    <input type="text" name="nombre" placeholder="Tu nombre">
                    <button type="submit">Saludar</button>
                </form>
            </body>
        </html>
    `);
});

app.listen(port, () => {
    console.log(`Servidor vulnerable corriendo en http://localhost:${port}`);
});