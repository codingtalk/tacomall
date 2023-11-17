import { storeToRefs } from "pinia";
import useAppStore from "@/store/app";

export const storeApp = () => {
  const { isLogin, member } = storeToRefs(useAppStore());
  const { getMemberInfo, logout } = useAppStore();
  return { isLogin, member, getMemberInfo, logout };
};
