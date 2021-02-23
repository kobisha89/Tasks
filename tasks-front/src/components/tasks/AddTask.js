import React from 'react';
import Axios from '../../apis/Axios';

class AddTask extends React.Component {

    constructor(props){
        super(props);

        let task = {
            name: "",
            employee: "",
            points: 0,
            sprint: null,
            state: null
        }

        this.state = {task: task, states: [], sprints: []};
    }

    componentDidMount(){
        this.getStates();
        this.getSprints();
    }

    getStates() {
        Axios.get('/states')
            .then(res => {
                 // handle success
                 console.log(res);
                 this.setState({states: res.data});
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Unable to reach states!');
            });
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
                alert('Unable to reach sprints!');
            });
    }

    onInputChange(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let task = this.state.task;
        task[name] = value;
    
        this.setState({ task: task });
        console.log(this.state.task)
    }

    sprintSelectionChange(e) {
        let sprintId = e.target.value;
        let sprint = this.state.sprints.find((sprint) => sprint.id == sprintId);
        console.log(sprint)

        let task = this.state.task;
        task.sprint = sprint;

        this.setState({task: task});
        console.log(this.state.task)
    }

    stateSelectionChange(e) {
        let stateId = e.target.value;
        let state = this.state.states.find((state) => state.id == stateId);
        console.log(state)

        let task = this.state.task;
        task.state = state;

        this.setState({task: task});
        console.log(this.state.task)
    }

    create(e) {
        e.preventDefault();

        let task = this.state.task;

            let taskDTO = {
                name: task.name,
                employee: task.employee,
                points: task.points,
                sprint: task.sprint,
                state: task.state
            }

        Axios.post('/tasks', taskDTO)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Task was added successfully!');
            this.props.history.push('/tasks');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Unable to create task!');
         });
    }

    render () {
        return (
            <div>
                <h1>Add task</h1>
                <label htmlFor="tName">Name</label><br/>
                <input id="tName" type="text" name="name" onChange={(e) => this.onInputChange(e)}/><br/>
                <label htmlFor="tEmployee">Employee</label><br/>
                <input id="tEmployee" type="text" name="employee" onChange={(e) => this.onInputChange(e)}/><br/>
                <label htmlFor="tPoints">Points</label><br/>
                <input id="tPoints" type="number" name="points" onChange={(e) => this.onInputChange(e)}/><br/>
                <label htmlFor="tSprint">Sprint</label><br/>
                <select id="tSprint" onChange={event => this.sprintSelectionChange(event)}>
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
                <label htmlFor="tState">State</label><br/>
                <select id="tState" onChange={event => this.stateSelectionChange(event)}>
                    <option></option>
                    {
                        this.state.states.map((state) => {
                            return (
                                <option key={state.id} value={state.id}>
                                    {state.name}
                                </option>
                            )
                        })
                    }
                </select><br/>

                <button className="button btn-primary" onClick={(event)=>{this.create(event);}}>Add</button>
            </div>
        )
    }
}

export default AddTask;