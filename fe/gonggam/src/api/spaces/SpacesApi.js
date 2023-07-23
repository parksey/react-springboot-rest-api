import axios from "axios";
import { API_VERSION, PROD_URL } from "../../constant/Constant";

// 전체 데이터 가져오기
export async function SpacesAllApi() {
  try {
    const response = await axios({
      method: "get",
      url: `${PROD_URL + API_VERSION}/spaces`,
      withCredentials: true,
    });
    return response.data;
  } catch (e) {
    console.log("get UserApi error!!");
  }
}

// 단일 space 가져오기
export async function SpaceApi(spaceId) {
  try {
    const response = await axios({
      method: "get",
      url: `${PROD_URL + API_VERSION}/spaces/${spaceId}`,
      withCredentials: true,
    });
    return response.data;
  } catch (e) {
    console.log("get UserApi error!!");
  }
}

// Space 저장
export async function SpaceSave(UserApiprops) {
  try {
    console.log(UserApiprops);
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION}/spaces`,
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
    return { success: false, message: e.data };
  }
}

// 사업자의 space들 데이터 가져오기
export async function MySpacesApi() {
  try {
    const response = await axios({
      method: "get",
      url: `${PROD_URL + API_VERSION}/spaces/mySpace`,
      withCredentials: true,
    });
    return response.data;
  } catch (e) {
    console.log("get MySpacesApi error!!");
  }
}

// 예약 요청
export async function RequestReserveApi(UserApiprops) {
  try {
    console.log(UserApiprops);
    const response = await axios({
      method: "post",
      url: `${PROD_URL + API_VERSION}/reservation`,
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      data: JSON.stringify(UserApiprops),
      withCredentials: true,
    });
    return { success: true, message: null };
  } catch (e) {
    console.log("get RequestReserveApi error!!");
    return { success: false, message: e.data };
  }
}

// 예약 리스트 가져오기
export async function MyReserveApi() {
  try {
    const response = await axios({
      method: "get",
      url: `${PROD_URL + API_VERSION}/myReservations`,
      withCredentials: true,
    });
    return response.data;
  } catch (e) {
    console.log("get RequestReserveApi error!!");
  }
}
