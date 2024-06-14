
import './css/App.css';
import './css/bootstrap/dist/css/bootstrap-grid.min.css'
import Login from "../src/components/Login";
import AdminDashboard from "../src/components/AdminDashboard";
import EmployeeDashboard from "../src/components/EmployeeDashboard";
import RoomPage from "../src/components/RoomPage";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import StockPage from "./components/StockPage";
import SettingsPage from "./components/SettingsPage";
import {UserProvider} from "./context/UserRoleContext";
import AddUserForm from "./components/AddUserForm";


function App() {
    return (
        <UserProvider>
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/AdminDashboard" element={<AdminDashboard />} />
                <Route path="/EmployeeDashboard" element={<EmployeeDashboard />} />
                <Route path="/RoomPage" element={<RoomPage/>}/>
                <Route path="/StockPage" element={<StockPage/>}/>
                <Route path="/SettingsPage" element={<SettingsPage/>}/>
                <Route path="/AddUserForm" element={<AddUserForm/>}/>
            </Routes>
        </Router>
        </UserProvider>
  );
}

export default App;
