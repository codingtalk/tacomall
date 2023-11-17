import dayjs from "dayjs";

export const timeFormat = (s, f = "YYYY-MM-D") => {
  return dayjs(s).format(f);
};
export default {
  timeFormat,
};
