import React, { Component } from 'react';
import {BrowserRouter,Route,HashRouter, Switch, Link} from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Login from './components/Login';
import Registration from './components/Registration';
import Nav from './components/Nav';
import Listof from './components/Listof';
import Adminlogin from './components/Adminlogin';
import Admindashboard from './components/Admindashboard';
import Accepted from './components/Accepted';
import Rejected from './components/Rejected';
import Intrested from './components/Intrested';
import Details from './components/Details';
import {compilerOptions} from './config.json';
import EditProfile from './components/Editprofile';
import Formvalidation from'./components/Formvalidation';

class App extends Component {
  render(){
    return (
      <div>
      <HashRouter>
      <div>
            <Switch>
               <Route path='/' component={Dashboard} exact />
               <Route path='/dashboard' component={Dashboard}/>
               <Route path='/listof/:profileIdParam' component={Listof}/>
               <Route path='/login' component={Login}/>
               <Route path='/adminDashboard' component={Admindashboard}/>
               <Route path='/adminLogin' component={Adminlogin}/>
               <Route path='/form' component={Formvalidation}/>
               <Route path='/register' component={Registration}/>
               <Route path="/accepted/:profileIdParam" component={Accepted}/>
               <Route path="/intrested/:profileId" component={Intrested}/>
               <Route path="/rejected/:profileIdParam" component={Rejected}/>
               <Route path='/details' component={Details}/>
               <Route path="/edit-profile" component={EditProfile} />
               {/* Other routes */}
            </Switch>
         </div>
      </HashRouter>
   </div>
  );
}
}
export default App;
