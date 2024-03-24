package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

public record LoginResponse(
        String token,
        Long expiresIn
) {
    public LoginResponse(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
