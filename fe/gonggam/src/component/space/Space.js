import { useNavigate, useParams } from "react-router-dom";
import { RequestReserveApi, SpaceApi } from "../../api/spaces/SpacesApi";
import { useEffect, useState } from "react";
import Header from "../Header";
import "../../style/Space.css";

function Space({ isLogin, setIsLogin }) {
  const navigate = useNavigate();
  const spaceId = useParams().spaceId;
  const mockSpace = {
    title: "",
  };
  const [space, setSpace] = useState(mockSpace);

  const ongetClick = async () => {
    const fetch = await RequestReserveApi({ spaceId }).then((response) => {
      if (response.success == true) {
        navigate("/");
      } else {
        alert("로그인 실패: " + response.message);
      }
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      const spacesList = await SpaceApi(spaceId);
      console.log(spacesList);
      setSpace(spacesList);
    };
    fetchData();
  }, []);

  const getTime = (time) => {
    const dateTime = new Date(time);
    const year = dateTime.getFullYear();
    const month = String(dateTime.getMonth() + 1).padStart(2, "0");
    const date = String(dateTime.getDate()).padStart(2, "0");
    const hour = String(dateTime.getHours()).padStart(2, "0");
    const minute = String(dateTime.getMinutes()).padStart(2, "0");
    return `[${year}/${month}/${date}  ${hour}:${minute}]`;
  };

  return (
    <>
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <div className="title">{space.title}</div>
      <div className="location-container">
        <i className="bi bi-geo-alt-fill location-icon"></i>
        <div className="location">{space.location}</div>
      </div>
      <div className="description-container">
        <div className="description">{space.description}</div>
      </div>

      <div className="info-container">
        <div className="capacity">
          <i class="bi bi-people-fill"></i>
          {space.capacity}
        </div>
        <div className="amount">
          <i className="bi bi-currency-dollar"></i>
          {space.amount}
        </div>
      </div>

      <div className="time">
        {getTime(space.startAt)}~{getTime(space.endAt)}
      </div>

      <div className="info-container">
        <button className="btn" onClick={ongetClick}>
          예약하기
        </button>
      </div>
    </>
  );
}

export default Space;
