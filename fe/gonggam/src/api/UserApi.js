import axios from "axios";
import { API_VERSION, PROD_URL } from "../constant/Constant";

const USER_API = "/users";
// 세션 체크
/**
 * 성공 = 현재 진행중인 작업
 * 실패 = 로그인 redirect
 */
export async function SendCheckSessionApi(sessionId) {
  try {
    const response = await axios({
      method: "get",
      url: PROD_URL + API_VERSION + USER_API + "/session",
      headers: {
        Authorization: `Bearer ${sessionId}`,
      },
    });
    return true;
  } catch (error) {
    // 원래는 로그인 페이지로 redirect
    return false;
  }
}

// 회원가입
export async function RegistApi(UserApiprops) {
  try {
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION + USER_API}/register`,
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      data: JSON.stringify(UserApiprops),
    });
    return { success: true, message: null };
  } catch (e) {
    console.log("RegistApi error!!");
    return { success: false, message: e.data };
  }
}

// 로그인
export async function LoginApi(UserApiprops) {
  try {
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION + USER_API}/login`,
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      data: JSON.stringify(UserApiprops),
      withCredentials: true,
    });
    return { success: true, message: null };
  } catch (e) {
    console.log("get UserApi error!!");
    return { success: false, message: "회원 로그인 실패" };
  }
}

async function checkAxios() {
  return await axios({
    method: "get",
    url: `${PROD_URL + API_VERSION}/session`,
    withCredentials: true,
  });
}

export async function ValidSessionCheck() {
  try {
    await checkAxios();
    return true;
  } catch (e) {
    console.log("get UserApi error!!");
    alert("너 납치된거야");
    return false;
  }
}

export async function ValidLogin() {
  try {
    await checkAxios();
    return true;
  } catch (e) {
    return false;
  }
}

// 사업자 회원가입
export async function OwnerRegistApi(UserApiprops) {
  try {
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION}/owners`,
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      data: JSON.stringify(UserApiprops),
    });
    return { success: true, message: null };
  } catch (e) {
    console.log("RegistApi error!!");
    return { success: false, message: e.data };
  }
}

// 사업자 로그인
export async function OwnerLoginApi(UserApiprops) {
  try {
    console.log(UserApiprops);
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION}/owners/login`,
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      data: JSON.stringify(UserApiprops),
      withCredentials: true,
    });
    return { success: true, message: null };
  } catch (e) {
    console.log("get OwnerLoginApi error!!");
    return { success: false, message: "사업자 로그인 실패" };
  }
}
