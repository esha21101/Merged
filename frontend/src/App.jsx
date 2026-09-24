import { useState } from 'react'

function App() {
  const [language, setLanguage] = useState('java')
  const [issues, setIssues] = useState([])
  const [loading, setLoading] = useState(false)

  const findIssues = async () => {
    setLoading(true)
    try {
      const response = await fetch(`http://localhost:8080/api/issues?language=${language}`)
      const data = await response.json()
      setIssues(data)
    } catch (error) {
      console.error('Failed to fetch issues:', error)
    }
    setLoading(false)
  }

  return (
    <div>
      <h1>Merged</h1>
      <p>Find good-first-issues matched to your stack.</p>

      <select value={language} onChange={(e) => setLanguage(e.target.value)}>
        <option value="java">Java</option>
        <option value="javascript">JavaScript</option>
        <option value="python">Python</option>
      </select>

      <button onClick={findIssues}>Find Issues</button>

      {loading && <p>Loading...</p>}

      <ul>
        {issues.map((issue) => (
          <li key={issue.html_url}>
            <a href={issue.html_url} target="_blank" rel="noreferrer">
              {issue.title}
            </a>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App