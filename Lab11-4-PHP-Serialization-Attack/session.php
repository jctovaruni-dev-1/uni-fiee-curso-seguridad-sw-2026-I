<?php
// Clase legítima del sistema encargada de gestionar logs de auditoría
class LogFile {
    public $log_file = "activity.log";
    public $status_message = "User session initialized.\n";

    // Método que se ejecuta automáticamente al destruir el objeto
    public function __destruct() {
        file_put_contents($this->log_file, $this->status_message, FILE_APPEND);
    }
}

// Simulación de lectura de sesión serializada desde una Cookie
if (isset($_COOKIE['UserSession'])) {
    // ERROR CRÍTICO: Deserializar entrada directa del usuario sin validación
    $user_data = unserialize(base64_decode($_COOKIE['UserSession']));
}
?>