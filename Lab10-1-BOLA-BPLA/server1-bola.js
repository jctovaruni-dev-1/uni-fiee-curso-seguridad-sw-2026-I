const express = require('express');
const app = express();

app.use(express.json());

// Base de datos simulada
let users = { 100: { name: 'Alumno UNI', role: 'user', secret: 'ClaveSecreta100' }, 101: { name: 'Admin', role: 'admin', secret: 'ClaveSecreta101' } };

// Endpoint vulnerable (BOLA)
app.get('/api/user/:id/secret', (req, res) => {
    // FALLO: No verifica si el ID solicitado pertenece al usuario autenticado
    const userData = users[req.params.id];
    userData ? res.json(userData) : res.status(404).send('No encontrado');
});

// Endpoint vulnerable (BPLA)
app.patch('/api/user/:id', (req, res) => {
    // FALLO: Permite sobrescribir cualquier propiedad, incluyendo el 'role'
    users[req.params.id] = { ...users[req.params.id], ...req.body };
    res.json({ message: 'Usuario actualizado', user: users[req.params.id] });
});

app.listen(3000, () => console.log('API Vulnerable corriendo en http://localhost:3000'));