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

// Endpoint vulnerable (BPLA) - Remediacion
app.patch('/api/user/:id', (req, res) => {
    //Remediacion
    const allowdUpdates = ['name']
    const updates = Object.keys(req.body)
    const isValid = updates.every(update => allowdUpdates.includes(update))
    if (!isValid) {
        return res.status(400).json({
            error: "Campos no permitidos"
        })
    }
    //-- Si pasa la validacion, se ejecuta esto
    users[req.params.id] = { ...users[req.params.id], ...req.body };
    res.json({ message: 'Usuario actualizado', user: users[req.params.id] });
});

app.listen(3000, () => console.log('API Vulnerable corriendo en http://localhost:3000'));