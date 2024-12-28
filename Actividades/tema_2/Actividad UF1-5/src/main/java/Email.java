import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Email {
    private int id;
    private String destinatario;
    private String remitente;
    private String asunto;
    private String cuerpo;
}
