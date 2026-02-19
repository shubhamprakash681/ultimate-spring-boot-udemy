import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import "./App.css";

function App() {
  const { logOut, tokenData, logIn, token } = useContext(AuthContext);
  const [apiData, setAPIData] = useState<string | null>(null);

  const fetchData = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/home", {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "text/plain; charset=utf-8",
        },
      });

      if (res) setAPIData(await res.text());
    } catch (error) {
      setAPIData(null);
      console.warn("API ERROR: ", error);
    }
  };

  useEffect(() => {
    if (token) {
      fetchData();
    } else {
      setAPIData(null);
    }
  }, [token]);

  if (!token) return <button onClick={() => logIn()}>Login</button>;

  return (
    <div>
      <div>HI "{tokenData?.name}"</div>
      {apiData && (
        <div>
          Message from API: <span style={{ fontWeight: "bolder" }}>{apiData}</span>
        </div>
      )}
      <div>Token Data:</div>
      <pre>{JSON.stringify(tokenData, null, 2)}</pre>
      <button onClick={() => logOut()}>Logout</button>
    </div>
  );
}

export default App;
