const express = require('express');
const jwt = require('jsonwebtoken');
const app = express();
const PORT = 3000;

app.use(express.json());

// Clave secreta débil para propósitos del laboratorio
const SECRET_KEY = "super-secret-key-123";

app.get('/', (req, res) => {
    res.send('API de Pruebas de Seguridad Activa.');
});

// Endpoint vulnerable que simula una autenticación
app.post('/login', (req, res) => {
    const user = { id: 1, username: "admin" };
    // Genera un token usando jsonwebtoken obsoleto
    const token = jwt.sign(user, SECRET_KEY, { expiresIn: '1h' });
    res.json({ token });
});

app.listen(PORT, () => {
    console.log(`Servidor corriendo de forma nativa en el puerto ${PORT}`);
});