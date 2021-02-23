import React from 'react';
import Axios from '../../apis/Axios';

class Task extends React.Component {

    constructor(props){
        super(props);

        this.state = { 
            tasks: [],
            sprints:[],
            search: {name:"", sprintId: null},
            pageNo: 0, 
            totalPages: 0
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData() {
        this.getTasks(0);
        this.getSprints();
    }

    getSprints() {
        Axios.get('/sprints')
            .then(res => {
                 // handle success
                 console.log(res);
                 this.setState({sprints: res.data});
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    delete(taskId) {
        Axios.delete('/tasks/' + taskId)
        .then(res => {
            console.log(res);
            alert("Task is deleted!")
            window.location.reload()
        })
        .catch(error => {
            console.log(error);
            alert("Task is not deleted!")
        })
    }

    renderTasks() {
        return this.state.tasks.map ((task) => {
            return (
                <tr key={task.id}>
                    <td>{task.name}</td>
                    <td>{task.employee}</td>
                    <td>{task.points}</td>
                    <td>{task.sprint.name}</td>
                    <td>{task.state.name}</td>
                    <td><button className="button btn-primary" onClick={() => this.delete(task.id)}>Delete</button></td>
                </tr>
            )
        })
    }

    searchValueChange(e) {
        let control = e.target;

        let name = control.name
        let value = control.value

        let search = this.state.search

        search[name] = value

        this.setState({search:search})
        console.log(this.state.search)

    }

    getTasks(pageNo) {
        let config = {
            params: {
                pageNo: pageNo
            },
          }

        if (this.state.search.name != "") {
            config.params.name = this.state.search.name;
        }  
        if (this.state.search.sprintId != -1) {
            config.params.sprintId = this.state.search.sprintId;
        }  

        Axios.get('/tasks', config)
            .then(res => {
                 // handle success
                 console.log(res);
                 this.setState({
                    tasks: res.data,
                    totalPages:res.headers["total-pages"]});
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    search() {
        this.getTasks();
    }

    render () {
        return (
            <div>
                <h1>Tasks</h1>
                <form>
                <label htmlFor="tName">Task name</label><br/>
                <input id="tName" name="name" type="text" onChange={(e) => this.searchValueChange(e)}/><br/>
                <label htmlFor="tSprintId">Sprint</label><br/>
                <select id="tSprintId" name="sprintId" onChange={(e) => this.searchValueChange(e)}>
                <option></option>
                {
                    this.state.sprints.map((sprint) => {
                        return (
                            <option key={sprint.id} value={sprint.id}>
                                {sprint.name}
                            </option>
                        )
                    })
                }
                </select><br/>
                
                <button className="button btn-primary" onClick={()=>{this.search();}}>Search</button>
                </form>

                <table className="table table-dark">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Employee</th>
                        <th>Points</th>
                        <th>Sprint</th>
                        <th>State</th>
                        <th>Actions</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {this.renderTasks()}
                </tbody>
                </table>

                <div>
                    <button disabled={this.state.pageNo==0} onClick={() =>this.getTasks(this.state.pageNo = this.state.pageNo - 1)}>Previous</button>
                    <button disabled={this.state.pageNo==this.state.totalPages-1} onClick={() =>this.getTasks(this.state.pageNo = this.state.pageNo + 1)}>Next</button>
                </div>
            </div>
        )
    }
}

export default Task;