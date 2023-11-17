import { timeFormat } from "@/utils/filter";
import _ from "lodash";
import dayjs from "dayjs";
import { utils } from "codingtalk-uni-toolkit";
const { getDates, getChunkDates } = utils.day;

export const getWeek = (t, z) => {
  let cur = 0;
  let st = "";
  let et = "";
  const chunkDates = _.chunk(
    getChunkDates(timeFormat(undefined, "YYYY-MM-" + 1)),
    7
  );
  for (let i = 0; i < chunkDates.length; i++) {
    const vDates = chunkDates[i]
      .filter((j) => !!j.fullDate)
      .map((k) => k.fullDate);
      if (z !== undefined) {
        if (i === z) {
        cur = z;
        st = vDates[0];
        et = vDates[-1];
        break;
      }
    } else {
      if (vDates.includes(timeFormat(undefined))) {
        cur = i;
        st = vDates[0];
        et = vDates[-1];
        break;
      }
    }
  }
  return {
    cur,
    st,
    et,
  };
};

export const getMonth = (t, z) => {
  const n = z !== undefined ? timeFormat(undefined, `YYYY-${z + 1}-D`) : undefined;
  return {
    cur: z ? z : Number(timeFormat(undefined, "M")) - 1,
    st: dayjs(n).startOf("month").format("YYYY-MM-D"),
    et: dayjs(n).endOf("month").format("YYYY-MM-D"),
  };
};
