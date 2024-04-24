import Navbar from "./Navbar";
import Home from "./Home";
import Create from "./Create"
import BlogDetails from "./blogDetails";
import NotFound from "./NotFound";
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
          <div className="content">
            <Switch>
              <Route exact path="/">
                <Home />
              </Route>
              <Route path="/create">
                <Create />
              </Route>
              <Route path="/blogs/:id">
                <BlogDetails />  
              </Route>
              <Route path="*">
                <NotFound />
              </Route>
            </Switch>
          </div>
      </div>
    </Router>
  );
}

export default App;
