import type { TAuthConfig, TRefreshTokenExpiredEvent } from "react-oauth2-code-pkce";
import { envVars } from "../lib/constants";

export const authConfig: TAuthConfig = {
  clientId: envVars.keycloakClientId,
  authorizationEndpoint: envVars.authorizationEndpoint,
  tokenEndpoint: envVars.tokenEndpoint,
  redirectUri: envVars.redirectUrl,
  scope: "openid profile email offline_access",
  onRefreshTokenExpire: (event: TRefreshTokenExpiredEvent) => event.logIn(undefined, undefined, "popup"),
};
