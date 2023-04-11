const TOKEN_KEY = 'CHAT_GHI_TOKEN';
export function getTokenAUTH() {
    return localStorage.getItem(TOKEN_KEY);
}
