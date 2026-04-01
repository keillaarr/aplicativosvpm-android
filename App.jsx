import marinha from "./assets/marinha.png";

function App() {
  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <img src={marinha} style={styles.image} />

        <h1 style={styles.title}>🔧 Sistema em Manutenção</h1>

        <p style={styles.text}>
          Informamos que o aplicativo SVPM+ encontra-se temporariamente com instabilidade.
        </p>

        <p style={styles.text}>
          A equipe técnica já está atuando para a regularização do sistema no menor tempo possível.
        </p>

        <p style={styles.text}>
          Para acesso ao serviço, utilize o link abaixo por meio do navegador:
        </p>

        <a
          href="https://websitesvpm.marinha.mil.br/"
          target="_blank"
          rel="noopener noreferrer"
          style={styles.button}
        >
          Acessar pelo Navegador
        </a>
      </div>
    </div>
  );
}


const styles = {
  container: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #0b3d91, #001f4d)",
  },
  card: {
    backgroundColor: "white",
    padding: "40px",
    borderRadius: "12px",
    textAlign: "center",
    width: "90%",
    maxWidth: "400px",
  },
  image: {
    width: "180px",
    marginBottom: "20px",
  },
  title: {
    marginBottom: "20px",
    color: "#0b3d91",
  },
  text: {
    marginBottom: "15px",
    color: "#333",
  },
  button: {
    display: "inline-block",
    padding: "12px 20px",
    backgroundColor: "#0b3d91",
    color: "white",
    borderRadius: "8px",
    textDecoration: "none",
  },
};

export default App;
