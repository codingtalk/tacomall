export const validate = {
  isMobile (s) {
    return /^1[3456789]\d{9}$/.test(s);
  }
};
