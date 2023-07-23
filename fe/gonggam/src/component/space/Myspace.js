import { useParams } from "react-router-dom";
import { SpaceApi } from "../../api/spaces/SpacesApi";
import { useEffect, useState } from "react";
import Header from "../Header";
import "../../style/Space.css";

function Myspace({ isLogin, setIsLogin }) {
  const spaceId = useParams().spaceId;
  const mockSpace = {
    title: "",
  };
  const [space, setSpace] = useState(mockSpace);
  useEffect(() => {
    const fetchData = async () => {
      const spacesList = await SpaceApi(spaceId);
      setSpace(spacesList);
    };
    fetchData();
  }, []);
  console.log(space);

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
    </>
  );
}

export default Myspace;
